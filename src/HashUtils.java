
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
}
