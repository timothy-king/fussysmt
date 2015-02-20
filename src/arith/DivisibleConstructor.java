package fussysmt;

import java.util.*;

/**
 * Constructs n-ary functions that are single argument parametric and are not indentified
 * that go to bool. This corresponds to :chainable and :pairwise in the smt lib standard.
 * The two functions that matter are:
 *  (par (A) (ite Bool A A A))
 */
public final class DivisibleConstructor implements FunctionConstructor {

  protected static final Symbol sym = new StringSymbol("divisible");
  protected final Map<Integer, Function> divisibleFunctions;
  protected final Signature int2Bool;
  
  @Override
  public boolean fixedArity(){ return true; }
  @Override
  public int minArity(){ return 1; }
  @Override
  public boolean fixedSymbol(){ return true; }
  @Override
  public int numericIndentifiers() { return 1; }
  @Override
  public int sortParameters() { return 0; }

  
  public DivisibleConstructor(Sort intSort, Sort boolSort){
    int2Bool = SignatureImpl.mkUnary(intSort, boolSort);
    this.divisibleFunctions = new HashMap<Integer, Function>();
  }
  
  @Override
  public Function produce(FunctionQualifier fq){
    assert(fq.hasNumericIdentifiers());
    List<Integer> num_ids = fq.getNumericIdentifiers();
    assert(num_ids.size() == 1);
    int first = num_ids.get(0);
    assert(getDivisibleQualifier(first).equals(fq));
    return produce(first);
  }

  public Function produce(int n){
    if(divisibleFunctions.containsKey(n)){
      return divisibleFunctions.get(n);
    } else {
      FunctionQualifier fq = new IndentifiedFunctionQualifier(sym, 1, n);
      Function fun = new FunctionImpl(this, fq, int2Bool);
      divisibleFunctions.put(n, fun);
      return fun;
    }
  }

  
  public FunctionQualifier getDivisibleQualifier(int n){
    return produce(n).producedBy();
  }
  
  @Override
  public boolean equals(Object o){
    return (o != null) && (o instanceof DivisibleConstructor);
  }
}
