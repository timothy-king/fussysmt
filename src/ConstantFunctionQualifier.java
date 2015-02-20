package fussysmt;

import java.util.List;
public class ConstantFunctionQualifier extends AbstractFunctionQualifier {

  public ConstantFunctionQualifier(Symbol sym){
    super(sym, 0);
  }
  
  public List<Integer> getNumericIdentifiers(){
    return AbstractQualifier.emptyNumericIdentifiers;
  }
  
  public List<Sort> getSortParameters(){
    return AbstractQualifier.emptySortParameters;
  }
}
