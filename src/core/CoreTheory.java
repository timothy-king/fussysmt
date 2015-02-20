package fussysmt;

import java.util.*;

/* The non-pure boolean parts from http://smtlib.cs.uiowa.edu/theories/Core.smt2
 *  (par (A) (= A A Bool :chainable))
 *  (par (A) (distinct A A Bool :pairwise))
 *  (par (A) (ite Bool A A A))
 */
public class CoreTheory {

  public final EqualityConstructor equalityConstructor;
  public final DistinctConstructor distinctConstructor;
  public final IteConstructor iteConstructor;
  public final Sort boolSort;

  public CoreTheory(Sort boolSort){
    this.boolSort = boolSort;
    equalityConstructor = new EqualityConstructor(boolSort);
    distinctConstructor = new DistinctConstructor(boolSort);
    iteConstructor = new IteConstructor(boolSort);
  }
}
