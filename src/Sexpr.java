
package fussysmt;

/** Classes that implement this directly print a valid smt sexpr. */
public interface Sexpr {
  public void writeSexpr(SexprStream ss);
}
