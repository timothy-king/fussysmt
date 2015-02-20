package fussysmt;

import java.util.*;

/**
 * :sorts ((Array 2))
 * :funs ((par (X Y) (select (Array X Y) X Y))
 *         (par (X Y) (store (Array X Y) X Y (Array X Y))) )
 */
public class ArraysExTheory {
  public final ArrayConstructor arrayConstructor;
  public final SelectConstructor selectConstructor;
  public final StoreConstructor storeConstructor;

  public ArraysExTheory(){
    arrayConstructor = new ArrayConstructor();
    selectConstructor = new SelectConstructor();
    storeConstructor = new StoreConstructor();
  }
}
