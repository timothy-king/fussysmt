package fussysmt;

/*
 * request ::= UserDepthChange (int delta)
 *           | TermDepthChange (int delta)
 *           | Hole (Sort)  ; Fill this hole via a sort
 *           | SortBinding(Sort)     ; bind in the current scope.
 *           | VarBinding(Function)  ; bind in the current scope.
 *           | NameBinding(Function) ; bind until next user scope going forward.
 *                                   ; This is for the :named attribute.
 */
public enum RequestKind {
  UserDepthChange,
  TermDepthChange,
  Hole,
  SortBinding,
  VarBinding,
  NameBinding;
  
}
