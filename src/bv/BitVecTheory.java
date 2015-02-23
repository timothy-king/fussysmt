package fussysmt;

import java.util.*;

public final class BitVecTheory {

  protected BitVecSortConstructor bvSortConstructor;
  protected List<SortConstructor> sortConstructors;
  protected List<FunctionConstructor> functionConstructors;
  protected Sort boolSort;
  
  public BitVecTheory(Sort boolSort){
    assert(BoolSortConstructor.isBoolSort(boolSort));

    this.bvSortConstructor = new BitVecSortConstructor();
    this.sortConstructors = new ArrayList<SortConstructor>();
    this.functionConstructors = new ArrayList<FunctionConstructor>();
    this.boolSort = boolSort;

    this.sortConstructors.add(this.bvSortConstructor);
    this.functionConstructors.add(new ExtractConstructor(bvSortConstructor));
    this.functionConstructors.add(new ConcatConstructor(bvSortConstructor));

    throw new RuntimeException("add bv constants");
    //Constant families
    //(_ bvX n)
    // All binaries #bX of sort (_ BitVec m) where m is the number of digits in X.
    // All hexadeximals #xX of sort (_ BitVec m) where m is 4 times the number of
  }
}
