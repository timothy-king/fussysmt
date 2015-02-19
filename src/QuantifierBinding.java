package fussysmt;

public class QuantifierBinding implements Sexpr {
  public final Symbol variable;
  public final Sort sort;

  public QuantifierBinding(Symbol v, Sort sort){
    this.variable = v;
    this.sort = sort;
  }

  public boolean equals(Object o){
    if(o == this){
      return true;
    }else if(o == null || !(o instanceof QuantifierBinding)){
      return false;
    }else{
      QuantifierBinding qb = (QuantifierBinding)o;
      return variable.equals(qb.variable) && sort.equals(qb.sort);
    }
  }

  public void writeSexpr(SexprStream ss){
    ss.open();
    ss.append(variable);
    ss.append(sort);
    ss.close();
  }
}
