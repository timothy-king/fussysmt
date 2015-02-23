package fussysmt;

import java.util.*;

/**
 * This corresponds to either:
 * http://smtlib.cs.uiowa.edu/theories/Ints.smt2
 * http://smtlib.cs.uiowa.edu/theories/Reals.smt2
 * http://smtlib.cs.uiowa.edu/theories/Reals_Ints.smt2
 */
public final class ArithTheory {
  enum ArithTheoryKind {
    INTS,
    REALS,
    REAL_INTS
  };

  protected ArithTheoryKind kind;

  protected List<SortConstructor> sortConstructors; // either {Int}, {Real} or {Real,Int} 
  protected List<FunctionConstructor> functionConstructors;
  protected Sort boolSort;
  
  public ArithTheory(ArithTheoryKind kind, Sort boolSort){
    assert(BoolSortConstructor.isBoolSort(boolSort));
    
    this.kind = kind;
    this.sortConstructors = new ArrayList<SortConstructor>();
    this.functionConstructors = new ArrayList<FunctionConstructor>();
    this.boolSort = boolSort;

    switch(kind){
    case INTS:
      {
        IntSortConstructor isc = new IntSortConstructor();
        Sort intSort = isc.produce();
        List<FunctionConstructor> shared = addSharedFunctions(intSort, boolSort);
        List<FunctionConstructor> intSpecific = addIntegerSpecificFunctions(intSort, boolSort);
        FunctionConstructor numeral = mkNumeral(intSort);
        
        sortConstructors.add(isc);
        functionConstructors.addAll(shared);
        functionConstructors.addAll(intSpecific);
        functionConstructors.add(numeral);
      }
      break;
    case REALS:
      {
        RealSortConstructor rsc = new RealSortConstructor();
        Sort realSort = rsc.produce();
        List<FunctionConstructor> shared = addSharedFunctions(realSort, boolSort);
        FunctionConstructor numeral = mkNumeral(realSort);
        FunctionConstructor decimal = mkDecimal(realSort);
        
        sortConstructors.add(rsc);
        functionConstructors.addAll(shared);
        functionConstructors.add(numeral);
        functionConstructors.add(decimal);
      }
      break;
    case REAL_INTS:
      {
        IntSortConstructor isc = new IntSortConstructor();
        Sort intSort = isc.produce();
        List<FunctionConstructor> intShared = addSharedFunctions(intSort, boolSort);
        List<FunctionConstructor> intSpecific = addIntegerSpecificFunctions(intSort, boolSort);
        FunctionConstructor intNumeral = mkNumeral(intSort);

        RealSortConstructor rsc = new RealSortConstructor();
        Sort realSort = rsc.produce();
        List<FunctionConstructor> realShared = addSharedFunctions(realSort, boolSort);
        FunctionConstructor decimal = mkDecimal(realSort);
        
        sortConstructors.add(isc);
        sortConstructors.add(rsc);
        functionConstructors.addAll(intShared);
        functionConstructors.addAll(realShared);
        functionConstructors.addAll(intSpecific);
        functionConstructors.add(intNumeral);
        functionConstructors.add(decimal);
      }
      break;
    }
    
    // :funs ((NUMERAL Int) 
    //     (- Int Int)                 ; negation
    //     (- Int Int Int :left-assoc) ; subtraction
    //     (+ Int Int Int :left-assoc) 
    //     (* Int Int Int :left-assoc)
    //     (div Int Int Int :left-assoc)
    //     (mod Int Int Int)
    //     (abs Int Int)
    //     (<= Int Int Bool :chainable)
    //     (<  Int Int Bool :chainable)
    //     (>= Int Int Bool :chainable)
    //     (>  Int Int Bool :chainable)
    //     (DECIMAL Real) 
    //     (- Real Real)                  ; negation
    //     (- Real Real Real :left-assoc) ; subtraction
    //     (+ Real Real Real :left-assoc) 
    //     (* Real Real Real :left-assoc)
    //     (/ Real Real Real :left-assoc)
    //     (<= Real Real Bool :chainable)
    //     (<  Real Real Bool :chainable)
    //     (>= Real Real Bool :chainable)
    //     (>  Real Real Bool :chainable)
    //     (to_real Int Real)
    //     (to_int Real Int)
    //     (is_int Real Bool)
    //    )
      
  }

  static List<FunctionConstructor> addSharedFunctions(Sort arithSort, Sort boolSort){
    Signature arith2Arith = SignatureImpl.mkUnary(arithSort, arithSort);
    
    FunctionConstructor negate, sub, plus, mult, leq, lt, geq, gt;
    
    // (- Int Int)                 ; negation
    negate = new ConstantFunctionConstructor(new StringSymbol("-"), arith2Arith);

    // (- Int Int Int :left-assoc) ; subtraction
    sub = new NaryFunctionConstructor(new StringSymbol("-"), 2, arithSort);
    
    // (+ Int Int Int :left-assoc) 
    plus = new NaryFunctionConstructor(new StringSymbol("+"), 2, arithSort);

    // (* Int Int Int :left-assoc) 
    mult = new NaryFunctionConstructor(new StringSymbol("*"), 2, arithSort);
    
    // (<= Int Int Bool :chainable)
    leq = new NaryFunctionConstructor(new StringSymbol("<="), 2, arithSort, boolSort);

    // (< Int Int Bool :chainable)
    lt = new NaryFunctionConstructor(new StringSymbol("<"), 2, arithSort, boolSort);

    // (>= Int Int Bool :chainable)
    geq = new NaryFunctionConstructor(new StringSymbol(">="), 2, arithSort, boolSort);

    // (> Int Int Bool :chainable)
    gt = new NaryFunctionConstructor(new StringSymbol(">"), 2, arithSort, boolSort);

    return Arrays.asList(negate, sub, plus, mult, leq, lt, geq, gt);
  }
  
  private static List<FunctionConstructor> addIntegerSpecificFunctions(Sort intSort, Sort boolSort){
    Signature int2Int = SignatureImpl.mkUnary(intSort, intSort);
    Signature intAndInt2Int = SignatureImpl.mkNary(2, intSort, intSort);


    FunctionConstructor divisible, div, mod, abs;

    // :funs_description
    // "All ranked function symbols of the form
    //    ((_ divisible n) Int Bool)
    //  where n is a positive numeral.
    // "
    divisible = new DivisibleConstructor(intSort, boolSort);

    // (div Int Int Int :left-assoc)
    div = new NaryFunctionConstructor(new StringSymbol("div"), 2, intSort);
    
    // (mod Int Int Int)
    mod = new ConstantFunctionConstructor(new StringSymbol("mod"), intAndInt2Int);

    // (abs Int Int)
    abs = new ConstantFunctionConstructor(new StringSymbol("abs"), int2Int);

    return Arrays.asList(divisible, div, mod, abs);
  }

  public static FunctionConstructor mkNumeral(Sort arithSort){
    return new NumeralFunctionConstructor(arithSort);
  }
  public static FunctionConstructor mkDecimal(Sort realSort){
    return new DecimalFunctionConstructor(realSort);
  }
}
