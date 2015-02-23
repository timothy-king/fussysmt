package fussysmt;

public class ArraySort extends AbstractSort {
  ArraySort(Qualifier sq, SortConstructor sc){
    super(sq,sc);
    assert(sq.isParameteric());
    assert(sq.getSortParameters().size() == 2);
  }

  public boolean equals(Object o){
    if(o == this){
      return true;
    } else if((o == null) || !(o instanceof ArraySort)){
      return false;
    } else {
      ArraySort as = (ArraySort) o;
      return indexSort().equals(as.indexSort()) && valueSort().equals(as.valueSort());
    }
  }

  public Sort indexSort(){
    assert(sq.getSortParameters().size() == 2);
    return sq.getSortParameters().get(0);
  }

  public Sort valueSort(){
    assert(sq.getSortParameters().size() == 2);
    return sq.getSortParameters().get(1);
  }
  
  public int hashCode(){
    return HashUtils.hash(producedBy().getSymbol(), indexSort(), valueSort());
  }
  
  public static boolean isArraySort(Sort s){
    return s != null && s instanceof ArraySort;
  }
}
