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
  public int arity(){ return domainSorts.size(); }

  public static Signature mkUnary(Sort domain, Sort codomain){
    return new SignatureImpl(Arrays.asList(domain), codomain);
  }

  public static Signature mkNary(int n, Sort domain, Sort codomain){
    return new SignatureImpl(Collections.nCopies(n, domain), codomain);
  }
}
