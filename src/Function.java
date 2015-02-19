
package fussysmt;

import java.util.List;

/* Functions are *always* monomorphic. */
public interface Function extends Production<FunctionQualifier, FunctionConstructor> {
  public FunctionQualifier producedBy();
  public FunctionConstructor producer();

  /* It is unclear who "owns" a symbol. The qualifier must be able to generate it. */
  public Symbol getSymbol();
  public Signature getSignature();

  /* Reaches into the signature */
  public int arity();
  public List<Sort> getDomainSorts();
  public Sort getDomainSort(int i);
  public Sort getCodomainSort();

  public boolean canApply(List<Term> args);
}

