package fussysmt;
public class BoolSortConstructor extends ConstantSortConstructor {
  public static final BoolSortConstructor boolSortConstructor = new BoolSortConstructor();
  public static final Symbol boolSymbol = new StringSymbol("Bool");
  
  protected static final ConstantSortQualifier boolQualifier = new ConstantSortQualifier(boolSymbol);
  
  public BoolSortConstructor(){}

  public Sort produce(SortQualifier sq){
    assert(sq.equals(boolQualifier));
    return new BoolSort(sq, this);
  }

  public BoolSort produce(){
    Sort s = produce(boolQualifier);
    assert(s instanceof BoolSort);
    return (BoolSort)s;
  }

}
