
package fussysmt;
public interface Sort extends Production<SortQualifier, SortConstructor >, Sexpr {
  public SortQualifier producedBy();
  public SortConstructor producer();
}
