package fussysmt;

import java.util.*;

public class LetTerm extends AbstractTerm {
  protected List<VarBinding> bindings;
  protected Term term;

  protected static final Symbol letSymbol = new StringSymbol("let");
  
  public LetTerm(List<VarBinding> bindings, Term t){
    super(TermKind.LET, t.getSort());
    assert(bindings.size() >= 1);
    this.bindings = bindings;
    this.term = t;
  }
  
  public boolean equals(Object o){
    if(o == this){
      return true;
    }else if(o == null || !(o instanceof LetTerm)){
      return false;
    }else{
      LetTerm lt = (LetTerm)o;
      return bindings.equals(lt.bindings) && term.equals(lt.term);
    }
  }
  public void writeSexpr(SexprStream ss){
    ss.open();
    ss.append(letSymbol);
    ss.open();
    ss.append(bindings);
    ss.close();
    ss.append(term);
    ss.close();
  }
}
