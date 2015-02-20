
package fussysmt;

import java.util.List;

public interface FunctionQualifier extends Qualifier {
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

  /* The symbol of a function qualifier corresponds to the Function Constructor.*/
  @Override
  public Symbol getSymbol();

  @Override
  public List<Integer> getNumericIdentifiers();

  @Override
  public List<Sort> getSortParameters();

  @Override
  public boolean isParameteric();

  @Override
  public boolean hasNumericIdentifiers();

  @Override
  public boolean isConstant();

  @Override
  public boolean equals(Object o);
}
