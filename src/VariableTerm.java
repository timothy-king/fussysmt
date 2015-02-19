package fussysmt;

public class VariableTerm extends AbstractTerm {
  protected Symbol sym;

  public VariableTerm(Symbol sym, Sort sort){
    super(TermKind.VARIABLE, sort);
    this.sym = sym;
  }

  public Symbol getSymbol(){ return sym; }
  
  public boolean equals(Object o){
    if(o == this){
      return true;
    }else if(o == null || !(o instanceof VariableTerm)){
      return false;
    }else{
      VariableTerm vt = (VariableTerm) o;
      return sym.equals(vt.sym) && getSort().equals(vt.getSort());
    }
  }
  
  public void writeSexpr(SexprStream ss){
    ss.append(getSymbol());
  }
}
