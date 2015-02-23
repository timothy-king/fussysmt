package fussysmt;

public final class BitVecSort extends AbstractSort {
  private int width;
  
  public BitVecSort(Qualifier sq, SortConstructor sc){
    super(sq,sc);
    assert(sq.hasNumericIdentifiers());
    assert(sq.getNumericIdentifiers().size() == 1);
  }
  
  public int getWidth(){
    return sq.getNumericIdentifiers().get(0);
  }
  
  @Override
  public boolean equals(Object o){
    if(o == this){
      return true;
    } else if (o == null || !(o instanceof BitVecSort)){
      return false;
    } else {
      BitVecSort bvs = (BitVecSort) o;
      return getWidth() == bvs.getWidth();
    }
  }

  @Override
  public int hashCode(){
    return HashUtils.hash(producedBy().getSymbol().hashCode(), getWidth());
  }
}
