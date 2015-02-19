package fussysmt;

import java.util.List;

public class ApplicationTerm extends AbstractTerm{
  protected Function fun;
  protected List<Term> arguments;

  public ApplicationTerm(Function f, List<Term> args){
    super(TermKind.APPLY, f.getCodomainSort());
    fun = f;
    arguments = args;
    assert(f.canApply(args));
  }
  
  public List<Term> getArguments(){ return arguments; }
  public Function getFunction(){ return fun; }

  /** Two Applications are equal if the functions are equal and the agurments match. */
  public boolean equals(Object o){
    if(o == this){
      return true;
    }else if(o == null || !(o instanceof ApplicationTerm)){
      return false;
    }else{
      ApplicationTerm app = (ApplicationTerm)o;
      return fun.equals(app.fun) && arguments.equals(app.arguments);
    }
  }
  

  public void writeSexpr(SexprStream ss){
    if(arguments.isEmpty()){
      ss.append(fun.getSymbol());
    }else{
      ss.open();
      ss.append(fun.getSymbol());
      ss.append(arguments);
      ss.close();
    }
  }

}
