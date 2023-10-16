package crypto;

public class Logic {
    

     /*
     * todo: Exclusive OR. x and y must have the same length. x xor y is the same as x != y
     */
    public boolean[] xor(boolean[] x, boolean[] y){
        return null; 
    }

    /**
     * todo: This is the 'round' function. It is its own inverse. f(x,k) = (L(x) xor feistel(k, R(x))) || R(x)
     */
    public boolean[] f(boolean[] x, boolean[] k){
        return null; 
    }

    /*
     * todo: feistel(k,x) = P4 (s0 (L (k xor EP(x))) || s1 (R (k xor EP(x)))
     */
    public boolean[] feistel(boolean[] k, boolean[] x){
        return null; 
    }
}
