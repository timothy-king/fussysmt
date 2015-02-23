package fussysmt;

import java.util.List;
import java.util.Collections;
import java.util.Arrays;

public class FullQualifier extends AbstractQualifier {
  private Symbol sym;
  private List<Integer> num_ids;
  private List<Sort> sort_params;
  private int arity;

  public FullQualifier(Symbol s, int arity, List<Integer> num_ids, List<Sort> sort_params){
    assert(s != null);
    assert(arity >= 0);
    assert(num_ids != null);
    assert(sort_params != null);
    this.sym = s;
    this.arity = arity;
    this.num_ids = num_ids;
    this.sort_params = sort_params;
  }
  
  // Constant -> arity is 0
  // Fixed -> no numeric and no sort parameters
  public static Qualifier mkConstantFixedQualifier(Symbol s){
    return new FullQualifier(s, 0, AbstractQualifier.emptyNumericIdentifiers, AbstractQualifier.emptySortParameters);
  }

  public static Qualifier mkConstantNumeralQualifier(Symbol s, List<Integer> num_ids){
    return new FullQualifier(s, 0, num_ids, AbstractQualifier.emptySortParameters);
  }
  public static Qualifier mkConstantNumeralQualifier(Symbol s, Integer... num_ids){
    return mkConstantNumeralQualifier(s, Arrays.asList(num_ids));
  }

  public static Qualifier mkConstantSortQualifier(Symbol s,  List<Sort> sort_params){
    return new FullQualifier(s, 0, AbstractQualifier.emptyNumericIdentifiers, sort_params);
  }

  public static Qualifier mkConstantSortQualifier(Symbol s, Sort... sort_params){
    return mkConstantSortQualifier(s, Arrays.asList(sort_params));
  }
  public static Qualifier mkConstantNumeralSortQualifier(Symbol s, List<Integer> num_ids, List<Sort> sort_params){
    return new FullQualifier(s, 0, num_ids, sort_params);
  }

  public static Qualifier mkQualifier(Symbol s, int arity){
    return new FullQualifier(s, arity, AbstractQualifier.emptyNumericIdentifiers, AbstractQualifier.emptySortParameters);
  }
  
  public static Qualifier mkNumeralQualifier(Symbol s, int arity,  List<Integer> num_ids){
    return new FullQualifier(s, arity, num_ids, AbstractQualifier.emptySortParameters);
  }
  public static Qualifier mkNumeralQualifier(Symbol s, int arity, Integer... num_ids){
    return mkNumeralQualifier(s, arity, Arrays.asList(num_ids));
  }
  
  public static Qualifier mkSortQualifier(Symbol s, int arity, List<Sort> sort_params){
    return new FullQualifier(s, arity, AbstractQualifier.emptyNumericIdentifiers, sort_params);
  }
  public static Qualifier mkSortQualifier(Symbol s, int arity, Sort... sort_params){
    return mkSortQualifier(s, arity, Arrays.asList(sort_params));
  }
  
  public static Qualifier mkNumeralSortQualifier(Symbol s, int arity, List<Integer> num_ids, List<Sort> sort_params){
    return new FullQualifier(s, arity, num_ids, sort_params);
  }

  public Symbol getSymbol(){ return sym; }
  public List<Integer> getNumericIdentifiers(){ return num_ids; }
  public List<Sort> getSortParameters(){ return sort_params; }
  public int getArity(){ return arity; }
}
