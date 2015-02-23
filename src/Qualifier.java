package fussysmt;

import java.util.List;

public interface Qualifier{
  public Symbol getSymbol();
  public List<Integer> getNumericIdentifiers();
  public List<Sort> getSortParameters();
  public int getArity();

  /**
   * This is what you should use to print the symbol.
   * 
   * Examples:
   * (Qualifier ) -> Symbol
   * (true * 0 * [] * []) -> true
   * (extract * 1 * [9,8] * [(_ BitVec 32) ]) -> (_ extract 9 8)
   */
  public Symbol getQualifiedSymbol();
  
  /* !getSortParameters().isEmpty() */
  public boolean isParameteric();

  /* !getSortParameters().isEmpty() */
  public boolean hasNumericIdentifiers();

  /* !isParameteric() && !hasNumericIdentifiers */
  public boolean isConstant();

  @Override
  public boolean equals(Object o);
}
