package fussysmt;

import java.util.List;

public interface Qualifier{
  public Symbol getSymbol();
  public List<Integer> getNumericIdentifiers();
  public List<Sort> getSortParameters();

  /* !getSortParameters().isEmpty() */
  public boolean isParameteric();

  /* !getSortParameters().isEmpty() */
  public boolean hasNumericIdentifiers();

  /* !isParameteric() && !hasNumericIdentifiers */
  public boolean isConstant();

  public boolean equals(Object o);
}
