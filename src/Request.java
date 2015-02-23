package fussysmt;

/*
 * request ::= UserDepthChange (int delta)
 *           | TermDepthChange (int delta)
 *           | Hole (Sort)  ; Fill this hole via a sort
 *           | SortBinding(Sort)     ; bind in the current scope.
 *           | VarBinding(Function)  ; bind in the current scope.
 *           | NameBinding(Function) ; bind until next user scope going forward.
 *                                   ; This is for the :named attribute.
 *
 * This implements the algebraic datatypes above in a single class. Not very happy about this...
 */
public class Request {

  private final RequestKind requestKind;
  private final int delta;
  private final Sort sort;
  private final Function function;

  private static boolean hasDelta(RequestKind requestKind){
    return requestKind == RequestKind.UserDepthChange
      || requestKind == RequestKind.TermDepthChange;
  }
  private static boolean hasSort(RequestKind requestKind){
    return requestKind == RequestKind.Hole
      || requestKind == RequestKind.SortBinding;
  }
  private static boolean hasFunction(RequestKind requestKind){
    return requestKind == RequestKind.VarBinding
      || requestKind == RequestKind.NameBinding;
  }
  
  private Request(RequestKind rk, int d, Sort s, Function f){
    requestKind = rk;
    if(hasDelta(requestKind)){
      delta = d;
    }else{
      assert(d == 0);
      delta = 0;
    }
    if(hasSort(requestKind)){
      sort = s;
    }else{
      assert(s == null);
      sort = null;
    }
    if(hasFunction(requestKind)){
      function = f;
    }else{
      assert(f == null);
      function = null;
    }
  }

  public static Request mkUserDepthChange (int delta){
    return new Request(RequestKind.UserDepthChange, delta, null, null);
  }
  public static Request mkTermDepthChange (int delta){
    return new Request(RequestKind.TermDepthChange, delta, null, null);
  }
  public static Request mkHole(Sort s){
    return new Request(RequestKind.Hole, 0, s, null);
  }
  public static Request mkSortBinding(Sort s){
    return new Request(RequestKind.SortBinding, 0, s, null);
  }
  public static Request mkVarBinding (Function f){
    return new Request(RequestKind.VarBinding, 0, null, f);
  }
  public static Request mkNameBinding (Function f){
    return new Request(RequestKind.NameBinding, 0, null, f);
  }
  
  public RequestKind getRequestKind(){
    return requestKind;
  }
  
  public int getDelta(){
    assert(hasDelta(requestKind));
    return delta;
  }
  public Sort getSort(){
    assert(hasSort(requestKind));
    return sort;
  }
  
  public Function getFunction(){
    assert(hasFunction(requestKind));
    return function;
  }
}
