package fussysmt;

import java.util.*;

public class SignatureImpl implements Signature {
  protected final List<Sort> domainSorts;
  protected final Sort codomainSort;
  
  protected static final List<Sort> emptyDomainSorts =
    Collections.emptyList();

  
  public SignatureImpl(Sort codomainSort){
    assert(codomainSort != null);
    this.codomainSort = codomainSort;
    this.domainSorts = emptyDomainSorts;
  }
  public SignatureImpl(List<? extends Sort> domainSorts, Sort codomainSort){
    assert(codomainSort != null);
    this.codomainSort = codomainSort;
    this.domainSorts = emptyDomainSorts;
  }
    
  public List<Sort> getDomainSorts(){ return domainSorts; }
  public Sort getDomainSort(int i){ return domainSorts.get(i); }
  public Sort getCodomainSort(){ return codomainSort; }
  public int getArity(){ return domainSorts.size(); }

  public static Signature mkUnary(Sort domain, Sort codomain){
    return new SignatureImpl(Arrays.asList(domain), codomain);
  }

  public static Signature mkNary(int n, Sort domain, Sort codomain){
    return new SignatureImpl(Collections.nCopies(n, domain), codomain);
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
}
