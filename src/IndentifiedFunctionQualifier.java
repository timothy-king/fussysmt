package fussysmt;

import java.util.*;

class IndentifiedFunctionQualifier extends AbstractFunctionQualifier {
  private List<Integer> identifiers;

  public IndentifiedFunctionQualifier(Symbol sym, int arity, Integer... ids){
    super(sym, arity);
    this.identifiers = Arrays.asList(ids);
  }
    
  public List<Sort> getSortParameters(){
    return AbstractQualifier.emptySortParameters;
  }

  public List<Integer> getNumericIdentifiers(){
    return identifiers;
  }
}
