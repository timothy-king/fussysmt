package fussysmt;

import java.util.*;

/** (par (X Y) (select (Array X Y) X Y)) */
public final class StoreConstructor extends ParametricFixedFunctionConstructor {
  public StoreConstructor(){
    super(new StringSymbol("store"), 3);
  }
  
  @Override
  public boolean equals(Object o){
    return (o != null) && (o instanceof StoreConstructor);
  }

  @Override
  protected Function mkFunction(Sort s){
    assert(s != null);
    assert(s instanceof ArraySort);
    ArraySort as = (ArraySort) s;
    FunctionQualifier sfq = getFunctionQualifier(as);
    List<Sort> domain = Arrays.asList(as, as.indexSort(), as.valueSort());
    Signature sig = new SignatureImpl(domain, as);
    return new FunctionImpl(this, sfq, sig);
  }
}
