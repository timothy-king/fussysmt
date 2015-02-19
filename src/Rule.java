
package fussysmt;
public interface Rule<E extends Qualifier, P extends Production<E, ? > > {
  public P produce(E e);
}
