package fussysmt;

public class ConstantFunctionConstructor implements FunctionConstructor {
  protected final Symbol sym;
  protected final ConstantFunctionQualifier qualifier;
  protected final Signature sig;
  
  /* */
  public ConstantFunctionConstructor(Symbol sym, Sort domain){
    this.sym = sym;
    this.qualifier = new ConstantFunctionQualifier(sym);
    this.sig = new SignatureImpl(domain);
  }

  /* */
  public ConstantFunctionConstructor(Symbol sym, Signature sig){
    this.sym = sym;
    this.qualifier = new ConstantFunctionQualifier(sym);
    this.sig = sig;
  }
  
  public boolean fixedArity(){ return true; }
  public int minArity(){ return sig.arity(); }

  public boolean fixedSymbol(){ return true; }
  public int numericIndentifiers() { return 0; }
  public int sortParameters() { return 0; }
  
  public Function produce(FunctionQualifier fq){
    assert(qualifier.equals(fq));
    return produce();
  }

  public Function produce(){
    return new FunctionImpl(this, qualifier, sig);
  }
  
  public boolean equals(Object o){
    return o == this;
  }
}
