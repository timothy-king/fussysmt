package fussysmt;

import java.util.*;

/**
 *  :funs_description "
 *    All function symbols with declaration of the form
 *
 *      ((_ extract i j) (_ BitVec m) (_ BitVec n))
 *
 *    where
 *    - i,j,m,n are numerals
 *   - m > i >= j >= 0,
 *   - n = i-j+1
 *   "
 *
 * Extract is both numeric and sort parametric.
 * It takes 2 numeric parameters i and j, and the sort
 * parameter n. As it may need to create a (_ BitVec n) on the
 * fly, it takes a BitVecSortConstructor as an argument.
 */
public final class ExtractConstructor implements FunctionConstructor {
  protected final Symbol extractSymbol;
  protected final BitVecSortConstructor bvSortConstructor;
  protected final Map<Qualifier, Function> functions;
  
  
  ExtractConstructor(BitVecSortConstructor bvc){
    this.extractSymbol = new StringSymbol("extract");
    this.bvSortConstructor = bvc;
    this.functions = new HashMap<Qualifier, Function>();
  }
  
  
  /* "extract" * (arity 1) * [i,j] numeric identifiers * [BitVec m] */
  public Function produce(Qualifier fq){
    assert(extractSymbol.equals(fq.getSymbol()));
    assert(minArity() == fq.getArity());
    List<Integer> num_ids = fq.getNumericIdentifiers();
    assert(num_ids.size() == numericIndentifiers());
    int i = num_ids.get(0);
    int j = num_ids.get(1);
    List<Sort> sorts = fq.getSortParameters();
    assert(sorts.size() == sortParameters());
    Sort mSort = sorts.get(0);
    assert(mSort instanceof BitVecSort);
    BitVecSort mBitVec = (BitVecSort)mSort;
    return produce(i, j, mBitVec);
  }
  
  public Function produce(int i, int j, BitVecSort mSort){
    //*   - m > i >= j >= 0
    assert(mSort.getWidth() > i );
    assert(i >= j);
    assert(j >= 0);
    List<Integer> num_ids = Arrays.asList(i,j);
    List<Sort> sorts = Arrays.asList((Sort)mSort);
    Qualifier qual = FullQualifier.mkNumeralSortQualifier(extractSymbol, minArity(), num_ids, sorts);
    if(functions.containsKey(qual)){
      return functions.get(qual);
    } else {
      int n = i-j+1;
      BitVecSort nSort = bvSortConstructor.getBitVecSort(n);
      Signature sig = SignatureImpl.mkUnary(mSort, nSort);
      Function extract = new FunctionImpl(this, qual, sig);
      functions.put(qual, extract);
      return extract;
    }
  }

  public Qualifier getExtractQualifier(int i, int j, BitVecSort m){
    return produce(i, j, m).producedBy();
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
