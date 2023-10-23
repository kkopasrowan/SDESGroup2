package crypto;

/**
 * 
 */
public class Permutation {
    /*
     * todo: return a bit array which is the left half of the parameter, inp.
     */
    public static boolean[] lh(boolean[] inp){
        return null;
    }

    /*
     * todo: return a bit array which is the right half of the parameter, inp.
     */
    public static boolean[] rh(boolean[] inp) {
        return null; 
    }
    /**
     * Concatenate the two bit arrays, x || y
     * @author Kayla Weldon
     * @param x left hand side bits
     * @param y right hand side bits
     * @return x and y concatenated
     */
    public static boolean[] concat(boolean[] x, boolean[] y){
        int xLen = x.length;
        int yLen = y.length;
        boolean[] result = new boolean[xLen + yLen];
    
        for (int i = 0; i < xLen; i++) {
            result[i] = x[i];
        }
            
        for (int i = 0; i < yLen; i++) {
            result[xLen + i] = y[i];
        }
        return result; 
    }

    /**
     * todo: Expand and/or permute and/or select from the bit array, inp, producing an expanded/permuted/selected bit array. Use the expansion/permutation vector epv.
     */
    public static boolean[] expPerm(boolean[] inp, int[] epv){
        return null; 
    }
}
