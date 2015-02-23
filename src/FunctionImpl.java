package fussysmt;

import java.util.List;
import java.util.Iterator;

/* Functions are *always* monomorphic. */
public class FunctionImpl implements Function{
  protected final FunctionConstructor fc;
  protected final Qualifier fq;
  protected final Signature sig;
  protected final Symbol qualifiedSymbol;
  
  public FunctionImpl(FunctionConstructor fc, Qualifier fq, Signature sig){
    assert(fc != null);
    assert(fq != null);
    assert(sig != null);
    
    assert(fq.getArity() == sig.getArity());
    this.fc = fc;
    this.fq = fq;
    this.sig = sig;
    this.qualifiedSymbol = fq.getQualifiedSymbol();
  }


  /* Functions do not own symbols. The qualifier always owns the symbol. */
  public Symbol getSymbol(){ return qualifiedSymbol; }
  public Signature getSignature(){ return sig; }
  
  @Override
  public Qualifier producedBy(){ return fq; }
  @Override
  public FunctionConstructor producer() { return fc; }

  @Override
  public int getArity(){
    return producedBy().getArity();
  }
  
  @Override
  public List<Sort> getDomainSorts(){
    return getSignature().getDomainSorts();
  }
  
  @Override
  public Sort getDomainSort(int i){
    return getSignature().getDomainSort(i);
  }
  
  @Override
  public Sort getCodomainSort(){
    return getSignature().getCodomainSort();     
  }

  @Override
  public boolean canApply(List<Term> args){
    return getSignature().canApply(args);     
  }
  
  @Override
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

