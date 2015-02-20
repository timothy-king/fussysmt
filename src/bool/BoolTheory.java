package fussysmt;

import java.util.*;

// The pure boolean parts from http://smtlib.cs.uiowa.edu/theories/Core.smt2
// :funs ((true Bool)  
//        (false Bool)
//        (not Bool Bool)
//        (=> Bool Bool Bool :right-assoc)
//        (and Bool Bool Bool :left-assoc)
//        (or Bool Bool Bool :left-assoc)
//        (xor Bool Bool Bool :left-assoc))
public class BoolTheory {

  public final SortConstructor boolSortConstructor;
  public final Sort boolSort;
  public final FunctionConstructor trueConstructor;
  
  public final ConstantFunctionConstructor falseConstructor;
  
  
  public final ConstantFunctionConstructor notConstructor;

  public final NaryFunctionConstructor impliesConstructor;

  public final NaryFunctionConstructor andConstructor;

  public final NaryFunctionConstructor orConstructor;

  public final NaryFunctionConstructor xorConstructor;
  

  public List<SortConstructor> sortConstructors;
  public List<FunctionConstructor> functionConstructors;

  public BoolTheory(){
    boolSortConstructor = BoolSortConstructor.boolSortConstructor;
    boolSort = BoolSortConstructor.boolSort;
    sortConstructors = Arrays.asList(boolSortConstructor);
      
    trueConstructor = new ConstantFunctionConstructor(new StringSymbol("true"), boolSort);
    falseConstructor = new ConstantFunctionConstructor(new StringSymbol("false"), boolSort);

    Signature bool2Bool = SignatureImpl.mkUnary(boolSort, boolSort);
    
    notConstructor = new ConstantFunctionConstructor(new StringSymbol("not"), bool2Bool);
    impliesConstructor = new NaryFunctionConstructor(new StringSymbol("=>"), 2, boolSort);
    andConstructor = new NaryFunctionConstructor(new StringSymbol("and"), 2, boolSort);
    orConstructor = new NaryFunctionConstructor(new StringSymbol("or"), 2, boolSort);
    xorConstructor = new NaryFunctionConstructor(new StringSymbol("xor"), 2, boolSort);
    functionConstructors = Arrays.asList(trueConstructor, falseConstructor, notConstructor,
                                         impliesConstructor, andConstructor, orConstructor,
                                         xorConstructor);
  }
}
