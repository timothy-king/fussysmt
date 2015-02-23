package fussysmt;

import java.util.List;
import java.util.Collections;
public abstract class AbstractQualifier implements Qualifier {

  protected static final List<Integer> emptyNumericIdentifiers = Collections.emptyList();
  protected static final List<Sort> emptySortParameters = Collections.emptyList();

  @Override
  public abstract Symbol getSymbol();
  @Override
  public abstract List<Integer> getNumericIdentifiers();
  @Override
  public abstract List<Sort> getSortParameters();
  @Override
  public abstract int getArity();

  
  /* !getSortParameters().isEmpty() */
  @Override
  public boolean isParameteric(){
    return !getSortParameters().isEmpty();
  }

  /* !getSortParameters().isEmpty() */
  @Override
  public boolean hasNumericIdentifiers(){
    return !getSortParameters().isEmpty();
  }

  /* !isParameteric() && !hasNumericIdentifiers */
  @Override
  public boolean isConstant(){
    return !isParameteric() && !hasNumericIdentifiers();
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("{Qualifier,");
    sb.append("symbol: ");
    sb.append(getSymbol().symbol());
    sb.append(", numeric identifiers: ");
    sb.append(getNumericIdentifiers().toString());
    sb.append(", sort parameters: ");
    sb.append(getSortParameters().toString());
    sb.append("}");
    return sb.toString();
  }
  
  
  /* probably does not have to be overloaded */
  @Override
  public boolean equals(Object o){
    if(this == o){
      return true;
    }else if(o == null || !(o instanceof Qualifier)){
      return false;
    } else {
      Qualifier q = (Qualifier) o;
      return getArity() == q.getArity() &&
        getSymbol().equals(q.getSymbol()) &&
        getNumericIdentifiers().equals(q.getNumericIdentifiers()) &&
        getSortParameters().equals(q.getSortParameters());
    }
  }
  
  /* probably does not have to be overloaded */
  @Override
  public int hashCode(){
    return HashUtils.hash(getSymbol(), getNumericIdentifiers(), getSortParameters(), getArity());
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
