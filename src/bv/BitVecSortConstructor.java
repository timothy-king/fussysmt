package fussysmt;
import java.util.*;

public final class BitVecSortConstructor implements SortConstructor {

  public static final Symbol bvSymbol = new StringSymbol("BitVec");

  private final Map<Integer, BitVecSort> bvSorts;
  
  public BitVecSortConstructor(){
    bvSorts = new HashMap<Integer, BitVecSort>();
  }

  @Override
  public boolean equals(Object o){
    return (o != null) && (o instanceof BitVecSortConstructor);
  }

  @Override
  public boolean fixedSymbol(){ return true; }

  @Override
  public int numericIndentifiers() { return 1; }

  @Override
  public int sortParameters() { return 0; }

  @Override
  public Sort produce(Qualifier sq){
    assert(sq.isParameteric());
    List<Integer> num_ids = sq.getNumericIdentifiers();
    assert(num_ids.size() == numericIndentifiers());
    
    int width = num_ids.get(0);
    assert(width >= 1);

    assert(getBitVecQualifier(width).equals(sq) );
    return getBitVecSort(width);
  }
    
  public Qualifier getBitVecQualifier(int n){
    return getBitVecSort(n).producedBy();
  }

  public BitVecSort getBitVecSort(int n){
    if(bvSorts.containsKey(n)){
      return bvSorts.get(n);
    } else{
      Qualifier sq = FullQualifier.mkConstantNumeralQualifier(bvSymbol, n);
      BitVecSort bvs = new BitVecSort(sq, this);
      bvSorts.put(n, bvs);
      return bvs;
    }
  }

}
