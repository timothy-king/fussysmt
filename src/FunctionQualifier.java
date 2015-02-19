
package fussysmt;

public interface FunctionQualifier extends Qualifier {
  public int getArity();

  /* Examples:
   * (Qualifier ) -> Symbol
   * (true * 0 * [] * []) -> true
   * (extract * 1 * [9,8] * [(_ BitVec 32) ]) -> (_ extract 9 8)
   */
  public Symbol getQualifiedSymbol();
} 
