package crypto;
import static crypto.Permutation.*;
import static crypto.SDES.*;
import static crypto.Logic.*;

public class ByteHandler {
    /*
     * Encrypt a single byte using SDES
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
     */
    public static byte decryptByte(byte b){
        boolean[] afterIP = expPerm(byteToBitArray(b, 1), IP);
        boolean[] afterFk2 = f(afterIP, K2);
        boolean[] swapped = concat(afterIP, afterFk2);
        boolean[] afterFk1 = f(swapped, K1);
        return bitArrayToByte(expPerm(afterFk1, IP_INV));
    }

    /*
     * todo: Convert the given bit array to a single byte
     */
    public static byte bitArrayToByte(boolean[] inp){
        return (Byte) null; 
    }

     /*
     * todo: Convert the given byte to a bit array, of the given size.
     */
    public static boolean[] byteToBitArray(byte b, int size){
        return null; 
    }
}
