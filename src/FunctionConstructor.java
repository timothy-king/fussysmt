package fussysmt;

public interface FunctionConstructor extends Rule<Qualifier, Function> {

  /* symbol * (arity N) * [int] numeric identifiers * [sorts] */
  public Function produce(Qualifier fq);

  /** Returns true if the FunctionConstructor takes only one symbol.*/
  public boolean fixedSymbol();

  /** Returns true if the arity is fixed. */
  public boolean fixedArity();
  public int minArity();
  
  /** Returns the number of numerals needed to specify the function. */
  public int numericIndentifiers();
  
  /** Returns the number of sorts needed to specify the function. */
  public int sortParameters();
}
