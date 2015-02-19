package fussysmt;

import java.util.List;
public interface Signature {
  public List<Sort> getDomainSorts();
  public Sort getDomainSort(int i);
  public Sort getCodomainSort();
  public int arity();
}
