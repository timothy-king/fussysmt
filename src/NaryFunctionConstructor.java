package fussysmt;

import java.util.*;

/**
 * Constructs n-ary functions that are not parametric and are not indentified
 *   [domain]+ to codomain
 */
public class NaryFunctionConstructor implements FunctionConstructor {
  protected final Symbol sym;
  protected final Sort domain;
  protected final Sort codomain;
  protected final int minArity;

  private final Map<Integer, Signature> signatures;
  private final Map<Integer, FunctionQualifier> qualifiers;
  
  public NaryFunctionConstructor(Symbol sym, int minArity, Sort domain){
    this.sym = sym;
    this.domain = domain;
    this.codomain = domain;
    this.minArity = minArity;
    this.signatures = new HashMap<Integer, Signature>();
    this.qualifiers = new HashMap<Integer, FunctionQualifier>();
    assert(minArity >= 0);
  }

  /* Assumes the codomain matches the domain */
  public NaryFunctionConstructor(Symbol sym, int minArity, Sort domain, Sort codomain){
    this.sym = sym;
    this.domain = domain;
    this.codomain = codomain;
    this.minArity = minArity;
    this.signatures = new HashMap<Integer, Signature>();
    this.qualifiers = new HashMap<Integer, FunctionQualifier>();
    assert(minArity >= 0);
  }
  
  public boolean fixedArity(){ return false; }
  public int minArity(){ return minArity; }
  public boolean fixedSymbol(){ return true; }
  public int numericIndentifiers() { return 0; }
  public int sortParameters() { return 0; }
  
  public Function produce(FunctionQualifier fq){
    int arity = fq.getArity();
    assert(naryFunctionQualifier(arity).equals(fq));
    return produce(arity);
  }

  public Function produce(int n){
    return new FunctionImpl(this, naryFunctionQualifier(n), narySignature(n));
  }

  public FunctionQualifier naryFunctionQualifier(int n){
    assert(n >= minArity);
    if(qualifiers.containsKey(n)){
      return qualifiers.get(n);
    } else {
      FunctionQualifier fq = new NaryFunctionQualifier(sym, n);
      qualifiers.put(n, fq);
      return fq;
    }
  }
  
  public Signature narySignature(int n){
    assert(n >= minArity);
    if(signatures.containsKey(n)){
      return signatures.get(n);
    } else {
      Signature sig = SignatureImpl.mkNary(n, domain, codomain);
      signatures.put(n, sig);
      return sig;
    }
  }
  
  public boolean equals(Object o){
    return o == this;
  }
}
