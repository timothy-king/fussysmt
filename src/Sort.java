
/**
 * A sort is fully determined by its qualifier.
 * A sort is always produced by a sort constructor.
 * The arity of the sort is always 0.
 *
 * Examples:
 *  Bool : BoolSortConstructor applied to ("Bool", 0, [], [])
 *  (Array Bool Int) : ArrayConstructor applied to ("Array", 0, [], [Bool, Int])
 *  (_ BitVec n) : BitVecSortConstructor applied to ("BitVec", 0, [n], [])
 */
package fussysmt;
public interface Sort extends Production<Qualifier, SortConstructor >, Sexpr {
  @Override
  public Qualifier producedBy();

  @Override
  public SortConstructor producer();

  @Override
  public void writeSexpr(SexprStream ss);
}
