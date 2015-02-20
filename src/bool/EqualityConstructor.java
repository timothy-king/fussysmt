package fussysmt;

public class EqualityConstructor extends ChainableFunctionConstructor {  
  public EqualityConstructor(Sort boolSort){
    super(new StringSymbol("="), 2, boolSort);
    assert(BoolSortConstructor.isBoolSort(boolSort));
  }

  public boolean equals(Object o){
    return (o != null) && (o instanceof EqualityConstructor);
  }
}
