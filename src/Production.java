package fussysmt;
public interface Production<E extends Qualifier, R extends Rule<E, ? > > {
  public E producedBy();
  public R producer();
}
