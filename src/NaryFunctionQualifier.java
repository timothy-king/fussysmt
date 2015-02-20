package fussysmt;

import java.util.List;
public class NaryFunctionQualifier extends AbstractFunctionQualifier {

  public NaryFunctionQualifier(Symbol sym, int arity){
    super(sym, arity);
  }
  
  public List<Integer> getNumericIdentifiers(){
    return AbstractQualifier.emptyNumericIdentifiers;
  }
  
  public List<Sort> getSortParameters(){
    return AbstractQualifier.emptySortParameters;
  }
}
