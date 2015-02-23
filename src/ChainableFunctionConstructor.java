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
  private final Map<SortIntPair, Function> functions;

  public ChainableFunctionConstructor(Symbol sym, int minArity, Sort codomain){
    this.sym = sym;
    this.codomain = codomain;
    this.minArity = minArity;
    this.functions = new HashMap<SortIntPair, Function>();
    assert(minArity >= 0);
  }
  
  public boolean fixedArity(){ return false; }
  public int minArity(){ return minArity; }
  public boolean fixedSymbol(){ return true; }
  public int numericIndentifiers() { return 0; }
  public int sortParameters() { return 1; }
  
  public Function produce(Qualifier fq){
    assert(fq.isParameteric());
    List<Sort> params = fq.getSortParameters();
    assert(params.size() == 1);
    Sort first = params.get(0);

    int arity = fq.getArity();
    assert(naryFunctionQualifier(arity, first).equals(fq));
    return produce(arity, first);
  }

  
  public Function produce(int n, Sort s){
    assert(n >= minArity);    
    SortIntPair key = new SortIntPair(s,n);
    if(functions.containsKey(key)){
      return functions.get(key);
    } else {
      Qualifier fq = FullQualifier.mkSortQualifier(sym, n, s);
      Signature sig = SignatureImpl.mkNary(n, s, codomain);
      Function fun = new FunctionImpl(this, fq, sig);
      functions.put(key, fun);
      return fun;
    }
  }

  public Qualifier naryFunctionQualifier(int n, Sort s){
    return produce(n,s).producedBy();
  }
  
  public Signature narySignature(int n, Sort s){
    return produce(n,s).getSignature();
  }
  
  public abstract boolean equals(Object o);
}
