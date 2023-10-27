package crypto;

/**
 * @author Keegan Kopas
 */
public class Logic {
    

     /**
      * @author Keegan Kopas
     * todo: Exclusive OR. x and y must have the same length. x xor y is the same as x != y
     */
    public static boolean[] xor(boolean[] x, boolean[] y) throws IllegalArgumentException{
        if(x.length != y.length) throw new IllegalArgumentException("x and y must be of the same length.");
        boolean[] result = new boolean[x.length];
        for(int i = 0; i < x.length; i++) result[i] = Boolean.logicalXor(x[i], y[i]);
        return result; 

    }


    /**
     * @author Keegan Kopas
     * todo: This is the 'round' function. 
     *  f(x,k) = P4 (s0 (L (k xor EP(x))) || s1 (R (k xor EP(x)))
     */
    public static boolean[] f(boolean[] x, boolean[] k){
        return null; 
    }

    
    /**
     * @author Keegan Kopas
     * @param x
     * @return
     */
    public static boolean[] sboxOne(boolean[] x){

        boolean[][] s_1 = {
            {false, true},  //0  01
            {true, true},   //1  11
            {false, false}, //2  00 
            {true, false},  //3  10
            {true, true},   //4  11
            {false, true},  //5  01
            {true, false},  //6  10
            {false, false}, //7  00
            {false, false}, //8  00
            {true, true},   //9  11
            {true, false},  //10 10
            {false, true},  //11 01
            {false, true},  //12 01
            {true, true},   //13 11
            {true, true},   //14 11
            {true, false},  //15 10
        };
        
        return sboxGeneric(x, s_1);
    }

    /**
     * @author Keegan Kopas
     * @param x
     * @return
     */
    public static boolean[] sboxTwo(boolean[] x){

        boolean[][] s_1 = {
            {false, false}, //0  00
            {true, false},  //1  10
            {false, true},  //2  01
            {false, false}, //3  00
            {true, false},  //4  10 
            {true, true},   //5  11
            {true, true},   //6  11
            {true, true},   //7  11
            {true, true},   //8  11
            {true, false},  //9  10
            {false, false}, //10 00
            {false, true},  //11 01
            {false, true},  //12 01 
            {false, false}, //13 00
            {false, false}, //14 00
            {true, true},   //15 11
        };
        
        return sboxGeneric(x, s_1);
    }

    /**
     * @authbor Keegan KOpas
     * @param x
     * @param finalCollumn
     * @return
     */
    private static boolean[] sboxGeneric(boolean[] x, boolean[][] finalCollumn){
        if(x.length != 4) throw new IllegalArgumentException("Sbox requires a length of 4");
        int n = 0, l = x.length;
        for (int i = 0; i < l; ++i) {
            n = (n << 1) + (x[i] ? 1 : 0);
        }

        return finalCollumn[n];
    }


    /**
     * @author Keegan Kopas
     * @param num
     * @return
     */
    private static boolean[]  boolArrayFromNumber(int num) {
        if (num > 3) throw new IllegalArgumentException("Oversized value in sbox, boolArrayFromNumber");
        boolean [] flags = new boolean[2];
        for (int i = 0; i < 2; ++i) {
            flags[i] = (num & (1 << i)) != 0;
        }
        return flags; 
    }

    /**
     * @author Keegan Kopas
     * todo: feistel(k,x) =  R(x) || (L(x) xor f(R(x), k))
     */
    public static boolean[] feistel(boolean[] k, boolean[] x){
        boolean[] rightSide = Permutation.rh(x); 
        boolean[] leftSide = Permutation.lh(x); 
        boolean[] fOfRightSideK =  f(rightSide, k);
        return Permutation.concat(
            rightSide, 
            Logic.xor(leftSide, fOfRightSideK)
        ); 
    }

    /**
     * @author Keegan Kopas
     * todo: feistel(k,x) =  (R(x) xor f(L(x), k)) || L(x)
     */
    public static boolean[] feistel_decr(boolean[] k, boolean[] x){
        boolean[] rightSide = Permutation.rh(x); 
        boolean[] leftSide = Permutation.lh(x); 
        boolean[] fOfLeftSideK = f(leftSide, k);
        return Permutation.concat(
            Logic.xor(rightSide, fOfLeftSideK), 
            leftSide
        ); 
    }
}
