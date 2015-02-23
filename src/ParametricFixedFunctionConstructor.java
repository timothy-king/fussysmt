
package fussysmt;

/**
 * Generalizes function constructors that have a fixed arity, a fixed symbol
 * and are paramteric in exactly one sort qualifier:
 *
 * Examples:
 *  (par (X Y) (select (Array X Y) X Y))
 *  (bvnot (_ BitVec m) (_ BitVec m))
 */
import java.util.*;

public abstract class ParametricFixedFunctionConstructor implements FunctionConstructor {
  
  protected final Symbol functionSymbol;
  protected final Map<Sort, Function> functions;
  protected final int arity;

  public ParametricFixedFunctionConstructor(Symbol fs, int arity){
    this.functionSymbol = fs;
    this.arity = arity;
    this.functions = new HashMap<Sort, Function>();
  }

  @Override
  public abstract boolean equals(Object o);
  
  public Function produce(Qualifier fq){
    assert(fq.isParameteric());
    List<Sort> params = fq.getSortParameters();
    assert(params.size() == sortParameters());
    Sort param = params.get(0);
    assert(param instanceof ArraySort);
    ArraySort as = (ArraySort) param;
    return produce(as);
  }

  public Function produce(Sort s){
    if(functions.containsKey(s)){
      return functions.get(s);
    } else {
      Function fun = mkFunction(s);
      functions.put(s, fun);
      return fun;
    }
  }
  
  protected abstract Function mkFunction(Sort s);
  
  
  public Qualifier getFunctionQualifier(Sort s){
    return FullQualifier.mkSortQualifier(functionSymbol, minArity(), s);
  }
  
  /** Returns true if the FunctionConstructor produces only one symbol.*/
  @Override
  public boolean fixedSymbol(){ return true; }

  
  /** Returns true if the arity is fixed. */
  @Override
  public boolean fixedArity() { return true; }

  @Override
  public int minArity() { return arity; }
  
  
  /** Returns the number of numerals needed to specify the function. */
  @Override
  public int numericIndentifiers() { return 0; }
  
  /** Returns the number of sorts needed to specify the function. */
  @Override
  public int sortParameters() { return 1; }
}
