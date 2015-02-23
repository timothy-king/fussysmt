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

  private final Map<Integer, Function> functions;
  
  public NaryFunctionConstructor(Symbol sym, int minArity, Sort domain){
    this.sym = sym;
    this.domain = domain;
    this.codomain = domain;
    this.minArity = minArity;
    this.functions = new HashMap<Integer, Function>();
    assert(minArity >= 0);
  }

  /* Assumes the codomain matches the domain */
  public NaryFunctionConstructor(Symbol sym, int minArity, Sort domain, Sort codomain){
    this.sym = sym;
    this.domain = domain;
    this.codomain = codomain;
    this.minArity = minArity;
    this.functions = new HashMap<Integer, Function>();
    assert(minArity >= 0);
  }
  
  public boolean fixedArity(){ return false; }
  public int minArity(){ return minArity; }
  public boolean fixedSymbol(){ return true; }
  public int numericIndentifiers() { return 0; }
  public int sortParameters() { return 0; }
  
  public Function produce(Qualifier fq){
    int arity = fq.getArity();
    assert(naryFunctionQualifier(arity).equals(fq));
    return produce(arity);
  }

  public Function produce(int n){
    assert(n >= minArity);
    if(functions.containsKey(n)){
      return functions.get(n);
    } else {
      Signature sig = SignatureImpl.mkNary(n, domain, codomain);
      Qualifier fq = FullQualifier.mkQualifier(sym, n);
      Function fun = new FunctionImpl(this, fq, sig);
      functions.put(n, fun);
      return fun;
    }
  }

  public Qualifier naryFunctionQualifier(int n){
    return produce(n).producedBy();
  }
  
  public Signature narySignature(int n){
    return produce(n).getSignature();
  }
  
  public boolean equals(Object o){
    return o == this;
  }
}
