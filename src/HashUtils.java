
package fussysmt;

public class HashUtils {

  //http://www.boost.org/doc/libs/1_53_0/doc/html/hash/reference.html#boost.hash_combine
  public static int hash(Object... args){
    int seed = 0;
    for (Object o : args ){
      seed ^= (o.hashCode()) + 0x9e3779b9 + (seed << 6) + (seed >> 2);
    }
    return seed;
  }

  public static int hash(Iterable<? extends Object> iter){
    int seed = 0;
    for (Object o : iter ){
      seed ^= (o.hashCode()) + 0x9e3779b9 + (seed << 6) + (seed >> 2);
    }
    return seed;
  }
  
  public static class KeyPair<K1, K2> {
    private final K1 key1;
    private final K2 key2;
    
    public KeyPair(K1 k1, K2 k2){
      this.key1 = k1;
      this.key2 = k2;
    }

    @Override
    public boolean equals(Object o){
      if(o == null || !(o instanceof KeyPair) ){
        return false;
      } else {
        KeyPair kp = ( KeyPair ) o;
        return key1.equals(kp.key1) && key2.equals(kp.key2);
      }
    }

    @Override
    public int hashCode(){
      return HashUtils.hash(key1, key2);
    }
  }
}
