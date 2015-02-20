package fussysmt;
public class StringSymbol implements Symbol {
  protected final String sym;
  public StringSymbol(String sym){
    assert(sym != null);
    assert(isSymbol(sym));
    this.sym = sym;
  }

  public String symbol(){ return sym; }

  public int hashCode(){
    return sym.hashCode();
  }
  
  public static boolean isSymbol(String s){
    return isBasicSymbol(s) && isQualifiedSymbol(s);
  }

  public static boolean isBasicSymbol(String s){
    // TODO
    throw new UnsupportedOperationException();
  }
  
  public static boolean isQualifiedSymbol(String s){
    // TODO
    throw new UnsupportedOperationException();
  }
  
}
