package fussysmt;

public class BoolSort extends AbstractSort {
  public static final BoolSort boolSort = constructBoolSortConstructor();

  public BoolSort(SortQualifier sq, SortConstructor sc){
    super(sq, sc);
    assert(sq.isConstant());
    assert(sc.equals(BoolSortConstructor.boolSortConstructor));
  }

  private static BoolSort constructBoolSortConstructor(){
    BoolSortConstructor bsc = BoolSortConstructor.boolSortConstructor;
    return bsc.produce();
  }

  public boolean equals(Object o){
    return (o != null) && (o instanceof BoolSort);
  }
}
