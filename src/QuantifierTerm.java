package fussysmt;

import java.util.*;

public class QuantifierTerm extends AbstractTerm {
  protected final List<QuantifierBinding> boundVars;
  protected final Term term;
  protected final QuantifierKind quantKind;
  
  protected static final Symbol forallSymbol = new StringSymbol("forall");
  protected static final Symbol existsSymbol = new StringSymbol("exists");
  
  public QuantifierTerm(QuantifierKind qk, List<QuantifierBinding> boundVars, Term term){
    super(TermKind.QUANTIFIER, term.getSort());
    assert(term.getSort().equals(BoolSort.boolSort));
    this.quantKind = qk;
    this.boundVars=boundVars;
    this.term=term;
  }

  public boolean equals(Object o){
    if(o == this){
      return true;
    }else if(o == null || !(o instanceof QuantifierTerm)){
      return false;
    }else{
      QuantifierTerm qt = (QuantifierTerm)o;
      return boundVars.equals(qt.boundVars) && term.equals(qt.term);
    }
  }

  public static Symbol quantSymbol(QuantifierKind qk){
    switch(qk){
    case FORALL: return forallSymbol;
    case EXISTS: return existsSymbol;
    default:
      throw new IllegalArgumentException("Invalid QuantifierKind to quantSymbol");
    }
  }
  
  public void writeSexpr(SexprStream ss){
    ss.open();
    ss.append(quantSymbol(quantKind));
    ss.open();
    ss.append(boundVars);
    ss.close();
    ss.append(term);
    ss.close();
  }
}
