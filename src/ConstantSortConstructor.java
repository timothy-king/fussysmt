package fussysmt;

public abstract class ConstantSortConstructor implements SortConstructor {
  protected final Symbol sym;
  protected final ConstantSortQualifier qualifier;

  protected ConstantSortConstructor(Symbol sym){
    this.sym = sym;
    this.qualifier = new ConstantSortQualifier(sym);
  }
  protected Qualifier getConstantQualifier(){
    return qualifier;
  }
  
  public boolean fixedSymbol(){ return true; }
  public int numericIndentifiers() { return 0; }
  public int sortParameters() { return 0; }
  
  public Sort produce(Qualifier sq){
    assert(sq.equals(qualifier));
    return produce();
  }

  public abstract Sort produce();

  public abstract boolean equals(Object o);
}
