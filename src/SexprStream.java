
package fussysmt;
import java.util.*;

public interface SexprStream {
  public String lparen();
  public String rparen();
  public String whitespace();
  public String identifierPrefix();
  
  public void open();
  public void close();

  public int getDepth();
  
  public void append(Symbol s);
  public void append(Sexpr... sexprs);
  public void append(Iterable<? extends Sexpr> sexprs);

  /* numerics must be non-null and non-empty */
  public void appendIndentified(Symbol s, List<Integer> ids);
  
  public String toString();
}
