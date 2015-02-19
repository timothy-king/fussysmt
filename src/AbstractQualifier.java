package fussysmt;

import java.util.List;
import java.util.Collections;
public abstract class AbstractQualifier implements Qualifier {

  protected static final List<Integer> emptyNumericIdentifiers = Collections.emptyList();
  protected static final List<Sort> emptySortParameters = Collections.emptyList();
  
  public abstract Symbol getSymbol();
  public abstract List<Integer> getNumericIdentifiers();
  public abstract List<Sort> getSortParameters();
  
  /* !getSortParameters().isEmpty() */
  public boolean isParameteric(){
    return !getSortParameters().isEmpty();
  }

  /* !getSortParameters().isEmpty() */
  public boolean hasNumericIdentifiers(){
    return !getSortParameters().isEmpty();
  }

  /* !isParameteric() && !hasNumericIdentifiers */
  public boolean isConstant(){
    return !isParameteric() && !hasNumericIdentifiers();
  }

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
  public boolean equals(Object o){
    if(this == o){
      return true;
    }else if(o == null || !(o instanceof Qualifier)){
      return false;
    } else {
      Qualifier q = (Qualifier) o;
      return getSymbol().equals(q.getSymbol()) &&
        getNumericIdentifiers().equals(q.getNumericIdentifiers()) &&
        getSortParameters().equals(q.getSortParameters());
    }
  }
}
