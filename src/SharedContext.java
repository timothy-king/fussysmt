package fussysmt;

import java.util.Random;

/**
 * This is for shared generation information.
 * All generators get a copy of this.
 */
public class SharedContext {
  /** A copy of the shared random number generator. */
  public final Random random;

  /** A read only copy of the shared symbol table. */
  public final SymbolTable symbolTable;

  /** A read only copy of the shared scope. */
  public final Scope scope;

  /* Options go here. */
  
  public SharedContext(Random r, SymbolTable st, Scope sc){
    assert(r != null);
    assert(st != null);
    assert(sc != null);
    this.random = r;
    this.symbolTable = st;
    this.scope = sc;
  }

}
