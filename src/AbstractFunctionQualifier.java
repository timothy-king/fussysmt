package fussysmt;

import java.util.List;
public abstract class AbstractFunctionQualifier extends AbstractQualifier implements FunctionQualifier {

  protected final Symbol sym;
  protected final int arity;

  public AbstractFunctionQualifier(Symbol sym, int arity){
    assert(sym != null);
    assert(arity >= 0);
    this.sym = sym;
    this.arity = arity;
  }
  
  public abstract List<Integer> getNumericIdentifiers();
  public abstract List<Sort> getSortParameters();

  public int getArity() { return arity; }
  public Symbol getSymbol() { return sym; }

  public boolean equals(Object o){
    if(super.equals(o) && (o instanceof FunctionQualifier)){
      FunctionQualifier fq = (FunctionQualifier) o;
      return arity == fq.getArity();
    } else {
      return false;
    }
  }

  public Symbol getQualifiedSymbol(){
    if(hasNumericIdentifiers()){
      SexprStringStream ss = new SexprStringStream();
      ss.appendIndentified(getSymbol(), getNumericIdentifiers());
      String qualified = ss.toString();
      return new StringSymbol(qualified);
    }else{
      return getSymbol();
    }
  }

}
