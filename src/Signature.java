package fussysmt;

import java.util.List;
public interface Signature {
  public List<Sort> getDomainSorts();
  public Sort getDomainSort(int i);
  public Sort getCodomainSort();

  /** Matches getDomainSorts() */
  public int getArity();

  /**
   * args.size() == getArity() and
   * for all i. args.get(i).equals(getDomainSort(i))
   */
  public boolean canApply(List<Term> args);
}
