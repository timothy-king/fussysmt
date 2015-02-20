package fussysmt;

import java.util.*;

class ParametricFunctionQualifier extends AbstractFunctionQualifier {
  List<Sort> params;

  public ParametricFunctionQualifier(Symbol sym, int arity, Sort... params){
    super(sym, arity);
    this.params = Arrays.asList(params);
  }
    
  public List<Sort> getSortParameters(){
    return params;
  }

  public List<Integer> getNumericIdentifiers(){
    return AbstractQualifier.emptyNumericIdentifiers;
  }
}
