package fussysmt;
import java.util.List;

/**
 * request ::= UserDepthChange (int delta)
 *           | TermDepthChange (int delta)
 *           | Hole (Sort)  ; Fill this hole via a sort
 *           | SortBinding(Sort)     ; bind in the current scope.
 *           | VarBinding(Function)  ; bind in the current scope.
 *           | NameBinding(Function) ; bind until next user scope going forward.
 *                                   ; This is for the :named attribute.
 *
 * A term generator responds to a beginRequest(Sort s) for a Term of Sort s
 * by returning a List<Request>.
 *
 * Once the GeneratorProcessor can fulfill all of these requests,
 * it passes back the original list of requests, along with a list of
 * terms to fill each of the holes of the various sorts in the holes in the same order.
 * The term generator then generates a new term via endRequest().
 *
 * Requests have a stack based model.
 * A new request can be begun before ending an outstanding request,
 * but the requests must be balanced.
 *
 * For example:
 *  request i = beginRequest()
 *    request j = beginRequest()
 *    endRequest(j, ...)
 *    request k = beginRequest(k)
 *      ...
 *    endRequest(k, ...)
 *  endRequest(i, ...)
 */
public interface TermGenerator {

  /** See TermGenerator class documentation. */
  public List<Request> beginRequest(Sort s);

  /** See TermGenerator class documentation. */
  public Term endRequest(List<Request> requests, List<Term> filledHoles);


  /** Returns true if it can produce a term of the given sort.*/
  public boolean canFulfillRequest(Sort s);

  /**
   * Weight of the rule.
   * If the weight is negative, this should respond negatively to all canFulfillRequest() calls.
   */
  public double weight();

  /** Sets the weight of the rule. */
  public void setWeight(double d);

  /* Is notified at the end of beginRequest() and addRequestCalls() */
  public void addListener(GeneratorListener listener);
}
