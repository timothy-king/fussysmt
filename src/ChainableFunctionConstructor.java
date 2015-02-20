package fussysmt;

import java.util.*;

/**
 * Constructs n-ary functions that are single argument parametric and are not indentified
 * that go to bool. This corresponds to :chainable and :pairwise in the smt lib standard.
 * The two functions that matter are:
 *  (par (A) (= A A Bool :chainable))
 *  (par (A) (distinct A A Bool :pairwise))
 */
public abstract class ChainableFunctionConstructor implements FunctionConstructor {
  protected final Symbol sym;
  protected final Sort codomain;
  protected final int minArity;

  private class SortIntPair extends HashUtils.KeyPair<Sort, Integer> {
    SortIntPair(Sort s, Integer i) { super(s,i); }
  }
  
  private final Map<SortIntPair, Signature> signatures;
  private final Map<SortIntPair, FunctionQualifier> qualifiers;
  
  public ChainableFunctionConstructor(Symbol sym, int minArity, Sort codomain){
    this.sym = sym;
    this.codomain = codomain;
    this.minArity = minArity;
    this.signatures = new HashMap<SortIntPair, Signature>();
    this.qualifiers = new HashMap<SortIntPair, FunctionQualifier>();
    assert(minArity >= 0);
  }
  
  public boolean fixedArity(){ return false; }
  public int minArity(){ return minArity; }
  public boolean fixedSymbol(){ return true; }
  public int numericIndentifiers() { return 0; }
  public int sortParameters() { return 1; }
  
  public Function produce(FunctionQualifier fq){
    assert(fq.isParameteric());
    List<Sort> params = fq.getSortParameters();
    assert(params.size() == 1);
    Sort first = params.get(0);

    int arity = fq.getArity();
    assert(naryFunctionQualifier(arity, first).equals(fq));
    return produce(arity, first);
  }

  
  public Function produce(int n, Sort s){
    return new FunctionImpl(this, naryFunctionQualifier(n, s), narySignature(n, s));
  }

  public FunctionQualifier naryFunctionQualifier(int n, Sort s){
    assert(n >= minArity);
    SortIntPair key = new SortIntPair(s,n);
    if(qualifiers.containsKey(key)){
      return qualifiers.get(key);
    } else {
      FunctionQualifier fq = new ParametricFunctionQualifier(sym, n, s);
      qualifiers.put(key, fq);
      return fq;
    }
  }
  
  public Signature narySignature(int n, Sort s){
    assert(n >= minArity);
    SortIntPair key = new SortIntPair(s,n);
    if(signatures.containsKey(key)){
      return signatures.get(key);
    } else {
      Signature sig = SignatureImpl.mkNary(n, s, codomain);
      signatures.put(key, sig);
      return sig;
    }
  }
  
  public abstract boolean equals(Object o);
}
