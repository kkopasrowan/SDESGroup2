package crypto;

/**
 * 
 */
public class Permutation {

    /**
     * Left half of x, L(x)
     * @author Kayla Weldon
     * @param inp input of bits 
     * @return a bit array which is the left half of the parameter, inp
     * @throws IllegalArgumentException
     */
    public static boolean[] lh(boolean[] inp){
        int inpSize = inp.length;

        // check if inp is more than 1 bit
        if (inpSize < 2) {
            throw new IllegalArgumentException("Input bit array must have at least 2 bits.");
        }

        int resultSize = inpSize / 2;
        boolean[] result = new boolean[resultSize];
        //copy left half of bits
        System.arraycopy(inp, 0, result, 0, resultSize);

        return result;
    }

    /**
     * Right half of x, R(x)
     * @author Kayla Weldon
     * @param inp input of bits
     * @return a bit array which is the right half of the parameter, inp
     * @throws IllegalArgumentException
     */
    public static boolean[] rh(boolean[] inp) {
        int inpSize = inp.length;

        // check if inp is more than 1 bit
        if (inpSize < 2) {
            throw new IllegalArgumentException("Input bit array must have at least 2 bits.");
        }

        int resultSize = inpSize / 2;
        boolean[] result = new boolean[resultSize];
        // copy right half of bits
        System.arraycopy(inp, resultSize, result, 0, resultSize);

        return result;    
    }

    /**
     * Concatenate the two bit arrays, x || y
     * @author Kayla Weldon
     * @param x left hand side bits
     * @param y right hand side bits
     * @return x and y concatenated
     */
    public static boolean[] concat(boolean[] x, boolean[] y){
        int length = x.length + y.length;
        boolean[] result = new boolean[length];
        
        //concatenates x and y
        for (int i = 0; i < length; i++) {
            if (i < x.length) {
                result[i] = x[i];
            } else {
                result[i] = y[i - x.length];
            }
        }
        
        return result; 
    }

    /**
     * Expand and/or permute and/or select from the bit array, inp, 
     * producing an expanded/permuted/selected bit array. 
     * @author Kayla Weldon
     * @param inp - A bit array represented as booleans, true=1, false=0.
     * @param epv - An expansion and/or permutation and/or selection vector
     * @return The permuted/expanded/selected bit array, or null if there is an error.
     * @throws java.lang.IndexOutOfBoundsException
     */
    public static boolean[] expPerm(boolean[] inp, int[] epv){
        if(inp == null || epv == null) {
            return null;
        }
        
        boolean[] result = new boolean[epv.length];

        for(int i = 0; i < epv.length; i++) {
            //check epv at i to determine if in bounds
            if (epv[i] < 0 || epv[i] >= inp.length) {
                throw new IndexOutOfBoundsException("Index out of bounds: " + epv[i]);

            }
            //permute
            result[i] = inp[epv[i]];
        }
    
        return result;
    }
}
