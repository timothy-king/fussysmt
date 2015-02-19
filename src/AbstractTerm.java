package fussysmt;

public abstract class AbstractTerm implements Term {
  private TermKind kind;
  private Sort sort;

  protected AbstractTerm(TermKind tk, Sort s){
    kind = tk;
    sort = s;
  }
  
  public TermKind getKind(){ return kind; }
  public Sort getSort() { return sort; }

  /* what all subclasses must implement */
  public abstract boolean equals(Object o);
  public abstract void writeSexpr(SexprStream ss);

  /* No need to overload. */
  public String toString(){
    SexprStringStream ss = new SexprStringStream();
    writeSexpr(ss);
    return ss.toString();
  }
}
