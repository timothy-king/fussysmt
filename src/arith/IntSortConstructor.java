package fussysmt;

public final class IntSortConstructor extends ConstantSortConstructor {
  private final class IntSort extends AbstractSort {
    IntSort(SortQualifier sq, SortConstructor sc){
      super(sq,sc);
    }
    @Override
    public boolean equals(Object o){
      return (o != null) && (o instanceof IntSort);
    }
    @Override
    public int hashCode(){
      return producedBy().getSymbol().hashCode();
    }
  }
  
  public IntSortConstructor(){
    super(new StringSymbol("Int"));
  }

  @Override
  public Sort produce(){
    return new IntSort(qualifier, this);
  }

  @Override
  public boolean equals(Object o){
    return (o != null) && (o instanceof IntSortConstructor);
  }
}
