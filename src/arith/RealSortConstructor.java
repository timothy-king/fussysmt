package fussysmt;

public final class RealSortConstructor extends ConstantSortConstructor {
  private final class RealSort extends AbstractSort {
    RealSort(SortQualifier sq, SortConstructor sc){
      super(sq,sc);
    }
    @Override
    public boolean equals(Object o){
      return (o != null) && (o instanceof RealSort);
    }
    @Override
    public int hashCode(){
      return producedBy().getSymbol().hashCode();
    }
  }
  
  public RealSortConstructor(){
    super(new StringSymbol("Real"));
  }

  @Override
  public Sort produce(){
    return new RealSort(qualifier, this);
  }

  @Override
  public boolean equals(Object o){
    return (o != null) && (o instanceof RealSortConstructor);
  }
}
