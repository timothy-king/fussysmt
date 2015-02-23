package fussysmt;

import java.util.*;

/**
 *:funs_description "
 *    All function symbols with declaration of the form
 *
 *      (concat (_ BitVec i) (_ BitVec j) (_ BitVec m))
 *
 *    where
 *    - i,j,m are numerals
 *    - i,j > 0
 *    - i + j = m
 * "
 * Qualifier takes 2 sort arguments for i and j.
 * The qualifier has the shape:
 *   ("concat", 2, [], [(_ BitVec i), (_ BitVec j)])
 */
public final class ConcatConstructor implements FunctionConstructor {
  protected final Symbol concatSymbol;
  protected final BitVecSortConstructor bvSortConstructor;
  protected final Map<Qualifier, Function> functions;
  
  
  ConcatConstructor(BitVecSortConstructor bvc){
    this.concatSymbol = new StringSymbol("concat");
    this.bvSortConstructor = bvc;
    this.functions = new HashMap<Qualifier, Function>();
  }
  
  
  /**
   * Qualifier takes 2 sort arguments for i and j.
   * The qualifier has the shape:
   *   ("concat", 2, [], [(_ BitVec i), (_ BitVec j)])
   */
  public Function produce(Qualifier fq){
    assert(concatSymbol.equals(fq.getSymbol()));
    assert(minArity() == fq.getArity());
    assert(fq.getNumericIdentifiers().size() == numericIndentifiers());
    List<Sort> sorts = fq.getSortParameters();
    assert(sorts.size() == sortParameters());
    Sort iSort = sorts.get(0);
    Sort jSort = sorts.get(1);
    assert(iSort instanceof BitVecSort);
    assert(jSort instanceof BitVecSort);
    BitVecSort iBitVec = (BitVecSort)iSort;
    BitVecSort jBitVec = (BitVecSort)jSort;
    return produce(iBitVec, jBitVec);
  }
  
  public Function produce(BitVecSort iSort, BitVecSort jSort){
    /*    - i,j > 0
     *    - i + j = m
     */
    assert(iSort.getWidth() > 0 );
    assert(jSort.getWidth() > 0 );
    Qualifier qual = FullQualifier.mkSortQualifier(concatSymbol, minArity(), iSort, jSort);
    if(functions.containsKey(qual)){
      return functions.get(qual);
    } else {
      int m = iSort.getWidth() + jSort.getWidth();
      BitVecSort mSort = bvSortConstructor.getBitVecSort(m);
      Signature sig = new SignatureImpl(Arrays.asList(iSort,jSort), mSort);
      Function concat = new FunctionImpl(this, qual, sig);
      functions.put(qual, concat);
      return concat;
    }
  }

  public Qualifier getExtractQualifier(BitVecSort iSort, BitVecSort jSort){
    return produce(iSort, jSort).producedBy();
  }
  

  /** Returns true if the FunctionConstructor takes only one symbol.*/
  public boolean fixedSymbol(){ return true; }

  /** Returns true if the arity is fixed. */
  public boolean fixedArity(){ return true; }
  public int minArity(){ return 1; }
  
  /** Returns the number of numerals needed to specify the function. */
  public int numericIndentifiers(){ return 2; }
  
  /** Returns the number of sorts needed to specify the function. */
  public int sortParameters(){ return 1; }
}
