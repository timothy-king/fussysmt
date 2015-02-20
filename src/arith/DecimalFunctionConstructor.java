
package fussysmt;

import java.util.*;
import java.math.BigInteger;

public final class DecimalFunctionConstructor implements FunctionConstructor {
  private Map<String, Function> decimals;
  private Signature arithSig;
  
  DecimalFunctionConstructor(Sort arithSort){
    arithSig = new SignatureImpl(arithSort);
  }

  public static Symbol randomDecimal(Random r, int length){
    Symbol before = NumeralFunctionConstructor.randomNumeral(r, length);
    int numZeroes = r.nextInt(length);
    String zeroes = new String(new char[numZeroes]).replace("\0", "0");
    Symbol afterZeroes = NumeralFunctionConstructor.randomNumeral(r, length);
    return new StringSymbol(before.symbol() + "." + zeroes + afterZeroes);
  }
  
  public static boolean isDecimal(Symbol s){
    String str = s.symbol();
    int pos = str.indexOf('.');
    if(pos == -1){
      return false;
    } else {
      String before = str.substring(0, pos);
      String after = str.substring(pos);
      System.out.println("before and after" + before + " " + after);
      int leadingZeroes = 0;
      while(leadingZeroes < after.length()){
        if(after.charAt(leadingZeroes) == '0'){
          leadingZeroes++;
        }else{
          break;
        }
      }
      String afterZeroes = after.substring(leadingZeroes);
      System.out.println("before and after" + before + " " + after +" " + afterZeroes);
      return NumeralFunctionConstructor.isNumeral(new StringSymbol(before)) &&
        NumeralFunctionConstructor.isNumeral(new StringSymbol(afterZeroes));
    }
  }
  
  /* symbol * (arity N) * [int] numeric identifiers * [sorts] */
  public Function produce(FunctionQualifier fq){
    assert(fq.isConstant());
    assert(isDecimal(fq.getSymbol()));
    assert(getDecimalQualifier(fq.getSymbol()).equals(fq));
    return produce(fq.getSymbol());
  }

  public Function produce(Symbol key){
    if(decimals.containsKey(key.symbol())){
      return decimals.get(key.symbol());
    } else {
      assert(isDecimal(key));
      FunctionQualifier fq = new ConstantFunctionQualifier(key);
      Function fun = new FunctionImpl(this, fq, arithSig);
      decimals.put(key.symbol(), fun);
      return fun;
    }
  }

  public Function produce(Integer key){
    return produce(new StringSymbol(key.toString()));
  }
  
  public FunctionQualifier getDecimalQualifier(Symbol s){
    return produce(s).producedBy();
  }
  public FunctionQualifier getDecimalQualifier(Integer i){
    return produce(i).producedBy();
  }
  

  /** Each decimal has a different symbol. */
  @Override
  public boolean fixedSymbol() {return false; }

  /** Returns true if the arity is fixed. */
  public boolean fixedArity() { return true; }

  @Override
  public int minArity() { return 0; }
  
  /** Returns the number of decimals needed to specify the function. */
  @Override
  public int numericIndentifiers() { return 0; }

  /** Returns the number of sorts needed to specify the function. */
  @Override
  public int sortParameters() { return 0; }
}
