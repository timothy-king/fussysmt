package fussysmt;

public class BoolSortConstructor extends ConstantSortConstructor {
  public static final BoolSortConstructor boolSortConstructor = new BoolSortConstructor();
  public static final Symbol boolSymbol = new StringSymbol("Bool");

  public static final Sort boolSort = boolSortConstructor.produce();
  
  private class BoolSort extends AbstractSort {
    BoolSort(SortQualifier sq, SortConstructor sc){
      super(sq,sc);
    }
    public boolean equals(Object o){
      return (o != null) && (o instanceof BoolSort);
    }
    public int hashCode(){
      return boolSymbol.hashCode();
    }
  }
  
  public BoolSortConstructor(){
    super(boolSymbol);
  }

  public Sort produce(){
    return new BoolSort(qualifier, this);
  }

  public boolean equals(Object o){
    return (o != null) && (o instanceof BoolSortConstructor);
  }

  public static boolean isBoolSort(Sort s){
    return s != null && s instanceof BoolSort;
  }
}
