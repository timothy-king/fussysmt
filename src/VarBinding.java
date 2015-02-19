package fussysmt;

public class VarBinding implements Sexpr {
  public Symbol variable;
  public Term term;

  public VarBinding(Symbol v, Term t){
    this.variable = v;
    this.term = t;
  }

  public boolean equals(Object o){
    if(o == this){
      return true;
    }else if(o == null || !(o instanceof VarBinding)){
      return false;
    }else{
      VarBinding vb = (VarBinding)o;
      return variable.equals(vb.variable) && term.equals(vb.term);
    }
  }

  public void writeSexpr(SexprStream ss){
    ss.open();
    ss.append(variable);
    ss.append(term);
    ss.close();
  }
}
