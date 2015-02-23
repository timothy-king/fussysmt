package fussysmt;

import java.util.*;

/** (par (X Y) (select (Array X Y) X Y)) */
public final class SelectConstructor extends ParametricFixedFunctionConstructor {
  
  public SelectConstructor(){
    super(new StringSymbol("select"), 2);
  }

  @Override
  public boolean equals(Object o){
    return (o != null) && (o instanceof SelectConstructor);
  }
  
  @Override
  protected Function mkFunction(Sort s){
    assert(s != null);
    assert(s instanceof ArraySort);
    ArraySort as = (ArraySort) s;
    Qualifier sfq = getFunctionQualifier(as);
    Signature sig = new SignatureImpl(Arrays.asList(as, as.indexSort()), as.valueSort());
    return new FunctionImpl(this, sfq, sig);
  }
}
