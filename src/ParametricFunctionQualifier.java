package fussysmt;

import java.util.*;

class ParametricFunctionQualifier extends AbstractFunctionQualifier {
  List<Sort> params;

  public ParametricFunctionQualifier(Symbol sym, int arity, Sort... params){
    super(sym, arity);
    this.params = Arrays.asList(params);
  }

  @Override
  public List<Sort> getSortParameters(){
    return params;
  }

  @Override
  public List<Integer> getNumericIdentifiers(){
    return AbstractQualifier.emptyNumericIdentifiers;
  }
}
