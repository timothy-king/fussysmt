
package fussysmt;

public interface Term extends Sexpr {
  public Sort getSort();
  public TermKind getKind();
}
