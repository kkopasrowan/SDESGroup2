package crypto;
import static crypto.Permutation.*;
import static crypto.SDES.*;
import static crypto.Logic.*;

public class ByteHandler {
    /**
     * Encrypt a single byte using SDES
     * @author James Blake
     */
    public static byte encryptByte(byte b) {
        boolean[] afterIP = expPerm(byteToBitArray(b, 1), IP);
        boolean[] afterFk1 = f(afterIP, K1);
        boolean[] swapped = concat(afterIP, afterFk1);
        boolean[] afterFk2 = f(swapped, K2);
        return bitArrayToByte(expPerm(afterFk2, IP_INV));
    }

    /**
     * Decrypt a single byte using SDES
     * @author James Blake
     */
    public static byte decryptByte(byte b){
        boolean[] afterIP = expPerm(byteToBitArray(b, 1), IP);
        boolean[] afterFk2 = f(afterIP, K2);
        boolean[] swapped = concat(afterIP, afterFk2);
        boolean[] afterFk1 = f(swapped, K1);
        return bitArrayToByte(expPerm(afterFk1, IP_INV));
    }

    /**
     * Convert the given bit array to a single byte
     * 
     * @author Kayla Weldon
     * @param inp A bit array, max length is 8 bits
     * @return a byte corresponding to the given array of bits
     */
    public static byte bitArrayToByte(boolean[] inp){
        //check size of input
        if (inp.length > 8) {
            throw new IllegalArgumentException("Input bit array length cannot exceed 8 bits.");
        }
        byte result = 0;
        for (int i = 0; i < inp.length; i++){
            // if current bit is 1 set bit in result to 1
            if (inp[i]){
                result |= (1 << (7 - i));
            }
        }
        return result;
    }

     /**
     * Convert the given byte to a bit array, of the given size.
     * 
     * @author Kayla Weldon
     * @param b The byte to be converted to an array of bits
     * @param size The size of the resulting bit array.
     * @return an array of bits corresponding to the given byte
     */
    public static boolean[] byteToBitArray(byte b, int size){
        //checks size
        if (size < 1 || size > 8) {
            throw new IllegalArgumentException("Size must be between 1 and 8.");
        }
        boolean[] result = new boolean[size];
        //extract bits from byte
        for (int i = 0; i < size; i++) {
            //bitwise and to check if the bit is 1
            result[i] = (b & (1 << (7 - i))) != 0;
        }

        return result;    
    }
}
