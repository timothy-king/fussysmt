package fussysmt;

public abstract class AbstractSort implements Sort {
  protected final SortQualifier sq;
  protected final SortConstructor sc;

  // For now the sort must be either: constant, numeric or parameteric
  // It cannot be both numeric and parametric
  protected boolean constantNumericOrParametric(){
    return (sq.isConstant() || sq.isParameteric() || sq.hasNumericIdentifiers()) &&
      ( !sq.isParameteric() || !sq.hasNumericIdentifiers() );
  }
  
  protected AbstractSort(SortQualifier sq, SortConstructor sc){
    this.sq = sq;
    this.sc = sc;

    assert( constantNumericOrParametric());
  }

  public SortQualifier producedBy(){ return sq; }
  public SortConstructor producer(){ return sc; }

  public abstract boolean equals(Object o);

  public void writeSexpr(SexprStream ss){
    assert( constantNumericOrParametric());
    if(sq.isConstant()){
      ss.append(sq.getSymbol());
    }else if(sq.isParameteric()){
      ss.open();
      ss.append(sq.getSymbol());
      ss.append(sq.getSortParameters());
      ss.close();
    }else{
      assert(sq.hasNumericIdentifiers());
      ss.appendIndentified(sq.getSymbol(), sq.getNumericIdentifiers());
    }
  }

  /* No need to overload. */
  public String toString(){
    SexprStringStream ss = new SexprStringStream();
    writeSexpr(ss);
    return ss.toString();
  }
}
