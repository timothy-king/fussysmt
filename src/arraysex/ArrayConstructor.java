package fussysmt;

import java.util.*;

/**
 * :sorts ((Array 2))
 * :funs ((par (X Y) (select (Array X Y) X Y))
 *         (par (X Y) (store (Array X Y) X Y (Array X Y))) )
 */
public class ArrayConstructor implements SortConstructor {
  public static final Symbol arraySymbol = new StringSymbol("Array");

  private class SortPair extends HashUtils.KeyPair<Sort,Sort> {
    SortPair(Sort a, Sort b) { super(a,b); }
  }
  private final Map<SortPair, Sort> arraySorts;

  public ArrayConstructor(){
    arraySorts = new HashMap<SortPair, Sort>();
  }
  
  /* The symbol passed to sort qualifier changes. */
  public boolean fixedSymbol(){ return true; }
  public int numericIndentifiers() { return 0; }
  public int sortParameters() { return 2; }

  public Sort produce(Qualifier sq){
    assert(sq.isParameteric());
    List<Sort> params = sq.getSortParameters();
    assert(params.size() == 2);
    assert(getArraySortQualifier(params.get(0), params.get(1)).equals(sq) );
    return getArraySort(params.get(0), params.get(1));
  }

  public Qualifier getArraySortQualifier(Sort index, Sort value){
    return getArraySort(index, value).producedBy();
  }
  
  public Sort getArraySort(Sort index, Sort value){
    SortPair iv = new SortPair(index, value);
    if(arraySorts.containsKey(iv)){
      return arraySorts.get(iv);
    } else{
      Qualifier sq = FullQualifier.mkConstantSortQualifier(arraySymbol, index, value);
      ArraySort as = new ArraySort(sq, this);
      arraySorts.put(iv, as);
      return as;
    }
  }
  
  
  
  public boolean equals(Object o){
    return (o != null) && (o instanceof ArrayConstructor);
  }
  
}
