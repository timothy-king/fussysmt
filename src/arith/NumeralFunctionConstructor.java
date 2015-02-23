
package fussysmt;

import java.util.*;
import java.math.BigInteger;

public final class NumeralFunctionConstructor implements FunctionConstructor {
  private Map<String, Function> numerals;
  private Signature arithSig;
  
  NumeralFunctionConstructor(Sort arithSort){
    arithSig = new SignatureImpl(arithSort);
  }
  
  public static Symbol randomNumeral(Random r, int length){
    String s = (new BigInteger(length, r)).toString();
    Symbol sym = new StringSymbol(s);
    assert(isNumeral(sym));
    return sym;
  }
  
  public static boolean isNumeral(Symbol s){
    String str = s.symbol();
    if(str.length() <= 0){
      return false;
    } else if(str.equals("0")) {
      return true;
    } else if(str.charAt(0) == '0'){
      return false;
    } else {
      try{
        BigInteger big = new BigInteger(s.symbol());
      } catch(NumberFormatException nfe){
        return false;
      }
      return true;
    }
  }
  
  /* symbol * (arity N) * [int] numeric identifiers * [sorts] */
  public Function produce(Qualifier fq){
    assert(fq.isConstant());
    assert(isNumeral(fq.getSymbol()));
    assert(getNumeralQualifier(fq.getSymbol()).equals(fq));

    return produce(fq.getSymbol());
  }

  public Function produce(Symbol key){
    if(numerals.containsKey(key.symbol())){
      return numerals.get(key.symbol());
    } else {
      Qualifier fq = FullQualifier.mkConstantFixedQualifier(key);
      Function fun = new FunctionImpl(this, fq, arithSig);
      numerals.put(key.symbol(), fun);
      return fun;
    }
  }

  public Function produce(Integer key){
    return produce(new StringSymbol(key.toString()));
  }

  public Qualifier getNumeralQualifier(Symbol s){
    return produce(s).producedBy();
  }
  public Qualifier getNumeralQualifier(Integer i){
    return produce(i).producedBy();
  }
  

  /** Each numeral has a different symbol. */
  @Override
  public boolean fixedSymbol() {return false; }

  /** Returns true if the arity is fixed. */
  public boolean fixedArity() { return true; }

  @Override
  public int minArity() { return 0; }
  
  /** Returns the number of numerals needed to specify the function. */
  @Override
  public int numericIndentifiers() { return 0; }

  /** Returns the number of sorts needed to specify the function. */
  @Override
  public int sortParameters() { return 0; }
}
