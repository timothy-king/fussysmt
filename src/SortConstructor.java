package fussysmt;

public interface SortConstructor extends Rule<SortQualifier, Sort> {
  public Sort produce(SortQualifier sq);

  /** Returns true if the SortConstructor produces only one symbol.*/
  public boolean fixedSymbol();

  /** Returns the number of numerals needed to specify the sort. */
  public int numericIndentifiers();

  /** Returns the number of sorts needed to specify the sort. */
  public int sortParameters();
}
