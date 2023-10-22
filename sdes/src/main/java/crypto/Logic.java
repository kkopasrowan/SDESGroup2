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
     * todo: This is the 'round' function. It is its own inverse. f(x,k) = (L(x) xor feistel(k, R(x))) || R(x)
     */
    public static boolean[] f(boolean[] x, boolean[] k){
        return null; 
    }

    /*
     * todo: feistel(k,x) = P4 (s0 (L (k xor EP(x))) || s1 (R (k xor EP(x)))
     */
    public static boolean[] feistel(boolean[] k, boolean[] x){
        return null; 
    }
}
