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
        boolean[] tmp;
        boolean[] left;
        boolean[] right;

        tmp = expPerm(byteToBitArray(b, Byte.SIZE), IP); // IP

        left = lh(tmp);                               // L0
        right = rh(tmp);                              // R0

        tmp = xor(left, f(right, K0));                // L0 ^ f(R0, K0)
        left = right;                                 // L0 = R0
        right = tmp;                                  // R1 = L0 ^ f(R0, K0)

        tmp = xor(left, f(right, K1));                // L1 ^ f(R1, K1)
        left = right;                                 // L2 = R1
        right = tmp;                                  // R2 = L1 ^ f(R1, K1)

        return bitArrayToByte(expPerm(concat(left, right), IP_INV)); // IP_INV
    }

    /**
     * Decrypt a single byte using SDES
     * @author James Blake
     */
    public static byte decryptByte(byte b){
        boolean[] tmp;
        boolean[] left;
        boolean[] right;

        tmp = expPerm(byteToBitArray(b, 8), IP); // inverse(IP_INV) = IP

        left = lh(tmp);                               // L2 = R1
        right = rh(tmp);                              // R2

        tmp = xor(right, f(left, K1));                // L1 = R2 ^ F(R1, K1)
        right = left;                                 // R1 = L2
        left = tmp;                                   // L1 = R2 ^ F(R1, K1)

        tmp = xor(right, f(left, K0));                // L0 = R1 ^ F(R0, K0)
        right = left;                                 // R0 = L1
        left = tmp;                                   // L0 = R1 ^ F(R0, K0)

        return bitArrayToByte(expPerm(concat(left, right), IP_INV)); // inverse(IP) = IP_INV
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
