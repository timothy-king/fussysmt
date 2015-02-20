package fussysmt;

import java.util.List;
import java.util.Arrays;
public class ParametricSortQualifier extends AbstractQualifier implements SortQualifier {

  protected Symbol sym;
  protected List<Sort> params;
  
  public ParametricSortQualifier(Symbol sym, Sort... params){
    this.sym = sym;
    this.params = Arrays.asList(params);
  }
  
  public Symbol getSymbol(){ return sym; }
  
  public List<Integer> getNumericIdentifiers(){
    return AbstractQualifier.emptyNumericIdentifiers;
  }
  
  public List<Sort> getSortParameters(){
    return params;
  }
}
