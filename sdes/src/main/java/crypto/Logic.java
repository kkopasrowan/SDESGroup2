package crypto;

public class Logic {
    

     /*
     * todo: Exclusive OR. x and y must have the same length. x xor y is the same as x != y
     */
    public static boolean[] xor(boolean[] x, boolean[] y) throws IllegalArgumentException{
        if(x.length != y.length) throw new IllegalArgumentException("x and y must be of the same length.");
        boolean[] result = new boolean[x.length];
        for(int i = 0; i < x.length; i++) result[i] = Boolean.logicalXor(x[i], y[i]);
        return result; 

    }

    /**
     * todo: This is the 'round' function. 
     *  f(x,k) = P4 (s0 (L (k xor EP(x))) || s1 (R (k xor EP(x)))
     */
    public static boolean[] f(boolean[] x, boolean[] k){
        return null; 
    }

    /*
     * todo: feistel(k,x) =  R(x) || (L(x) xor f(R(x), k))
     */
    public static boolean[] feistel(boolean[] k, boolean[] x){
        return Permutation.concat(
            Permutation.rh(x), 
            Logic.xor(Permutation.lh(x), f(Permutation.rh(x), k))
        ); 
    }

    /*
     * todo: feistel(k,x) =  (R(x) xor f(L(x), k)) || L(x)
     */
    public static boolean[] feistel_decr(boolean[] k, boolean[] x){
        return Permutation.concat(
            Logic.xor(Permutation.rh(x), f(Permutation.lh(x), k)), 
            Permutation.lh(x)
        ); 
    }
}
