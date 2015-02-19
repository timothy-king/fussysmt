package fussysmt;

import java.util.List;
public class ConstantSortQualifier extends AbstractQualifier implements SortQualifier {

  protected Symbol sym;

  public ConstantSortQualifier(Symbol sym){
    this.sym = sym;
  }
  
  public Symbol getSymbol(){ return sym; }
  
  public List<Integer> getNumericIdentifiers(){
    return AbstractQualifier.emptyNumericIdentifiers;
  }
  
  public List<Sort> getSortParameters(){
    return AbstractQualifier.emptySortParameters;
  }
}
