package fussysmt;

import java.util.List;
import java.util.Iterator;

/* Functions are *always* monomorphic. */
public class FunctionImpl implements Function{
  protected final FunctionConstructor fc;
  protected final FunctionQualifier fq;
  protected final Signature sig;
  protected final Symbol qualifiedSymbol;
  
  public FunctionImpl(FunctionConstructor fc, FunctionQualifier fq, Signature sig){
    assert(fc != null);
    assert(fq != null);
    assert(sig != null);
    this.fc = fc;
    this.fq = fq;
    this.sig = sig;
    this.qualifiedSymbol = fq.getQualifiedSymbol();
  }
  
  public FunctionQualifier producedBy(){ return fq; }
  public FunctionConstructor producer() { return fc; }

  /* Functions do not own symbols. The qualifier always owns the symbol. */
  public Symbol getSymbol(){ return qualifiedSymbol; }
  public Signature getSignature(){ return sig; }

  /* Reaches into the signature */
  public int arity(){
    return getSignature().arity();
  }
  
  public List<Sort> getDomainSorts(){
    return getSignature().getDomainSorts();
  }

  public Sort getDomainSort(int i){
    return getSignature().getDomainSort(i);
  }
  
  public Sort getCodomainSort(){
    return getSignature().getCodomainSort();     
  }

  public boolean canApply(List<Term> args){
    List<Sort> sorts = getDomainSorts();
    
    if(args.size() != sorts.size()){
      return false;
    } else {
      Iterator<Term> term_iter = args.iterator();
      Iterator<Sort> sort_iter = sorts.iterator();

      while(term_iter.hasNext() ){
        Term t = term_iter.next();
        Sort s = sort_iter.next();
        if(!s.equals(t.getSort())){
          return false;
        }
      }
      return true;
    }
  }

  public boolean equals(Object o){
    if(o == this){
      return true;
    }else if(o == null || !(o instanceof Function)){
      return false;
    }else{
      Function f = (Function)o;
      return fq.equals(f.producedBy()) && fc.equals(f.producer()) && sig.equals(f.getSignature());
    }
  }

  public String toString(){
    return "{Function: "+ getSymbol()
      + ",  signature " + sig
      + ", producer: " + fc
      + ", applied to: " + fq
      + "}";
  }
}

