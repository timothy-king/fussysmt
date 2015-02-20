package fussysmt;

public class DistinctConstructor extends ChainableFunctionConstructor {  
  public DistinctConstructor(Sort boolSort){
    super(new StringSymbol("distinct"), 2, boolSort);
    assert(BoolSortConstructor.isBoolSort(boolSort));
  }

  public boolean equals(Object o){
    return (o != null) && (o instanceof DistinctConstructor);
  }
}
