package fussysmt;

public abstract class ConstantSortConstructor implements SortConstructor {
  
  public abstract Sort produce(SortQualifier sq);
  
  public boolean fixedSymbol(){ return true; }
  public int numericIndentifiers() { return 0; }
  public int sortParameters() { return 0; }
}
