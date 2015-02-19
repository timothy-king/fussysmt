
package fussysmt;
import java.util.*;

public class SexprStringStream implements SexprStream {
  protected StringBuilder sb;
  protected int depth;
  protected boolean hangingOpen;
  
  public SexprStringStream(){
    sb = new StringBuilder();
    depth = 0;
    hangingOpen = false;
  }
  
  public String lparen(){ return "("; }
  public String rparen(){ return ")"; }
  public String whitespace(){ return " "; }
  public String identifierPrefix(){ return "_"; }
  
  public void open(){
    depth++;
    append(lparen());
    hangingOpen = true;
  }
  public void close(){
    assert(depth >= 0);
    depth--;
    append(rparen());
    hangingOpen = false;
  }
  public int getDepth(){ return depth; }

  protected void resolveHangingOpen(){
    if(hangingOpen){
      hangingOpen = false;
    }else{
      append(whitespace());
    }
  }
  
  public void append(String s){
    resolveHangingOpen();
    sb.append(s);
  }
  public void append(Symbol s){
    resolveHangingOpen();
    append(s.symbol());
  }
  public void append(Sexpr... sexprs){ append(Arrays.asList(sexprs)); }

  public void append(Iterable<? extends Sexpr> sexprs){
    for(Sexpr s : sexprs){
      append(s);
    }
  }

  public String toString() { return sb.toString(); }

  public void appendIndentified(Symbol symbol, List<Integer> ids){
    open();
    append(identifierPrefix());
    append(symbol);
    for (Integer id : ids){
      assert(id >= 0);
      append(id.toString());
    }
    close();
  }

}
