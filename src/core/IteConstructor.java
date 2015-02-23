package fussysmt;

import java.util.*;

/**
 * Constructs n-ary functions that are single argument parametric and are not indentified
 * that go to bool. This corresponds to :chainable and :pairwise in the smt lib standard.
 * The two functions that matter are:
 *  (par (A) (ite Bool A A A))
 */
public class IteConstructor implements FunctionConstructor {
  protected static final Symbol sym = new StringSymbol("ite");
  protected final Sort boolSort;

  private final Map<Sort, Function> functions;
  
  public IteConstructor(Sort boolSort){
    this.boolSort = boolSort;
    this.functions = new HashMap<Sort, Function>();
  }
  
  public boolean fixedArity(){ return true; }
  public int minArity(){ return 3; }
  public boolean fixedSymbol(){ return true; }
  public int numericIndentifiers() { return 0; }
  public int sortParameters() { return 1; }
  
  public Function produce(Qualifier fq){
    assert(fq.isParameteric());
    List<Sort> params = fq.getSortParameters();
    assert(params.size() == 1);
    Sort first = params.get(0);
    assert(getIteFunctionQualifier(first).equals(fq));
    return produce(first);
  }

  
  public Function produce(Sort s){
    if(functions.containsKey(s)){
      return functions.get(s);
    } else {
      Signature sig = new SignatureImpl(Arrays.asList(boolSort, s, s), s);
      Qualifier fq = FullQualifier.mkSortQualifier(sym, 3, s);
      Function fun = new FunctionImpl(this, fq, sig);
      functions.put(s, fun);
      return fun;
    }
  }

  
  public Qualifier getIteFunctionQualifier(Sort s){
    return produce(s).producedBy();
  }
  
  public Signature getIteSignature(Sort s){
    return produce(s).getSignature();
  }
  
  public boolean equals(Object o){
    return (o != null) && (o instanceof IteConstructor);
  }
}
