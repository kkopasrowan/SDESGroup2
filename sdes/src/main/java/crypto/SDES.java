package crypto;

import java.util.Scanner;
import static crypto.ByteHandler.*;

public class SDES {
    public static final int[] P10 = new int[] {3, 5, 2, 7, 4, 10, 1, 9, 8, 6};
    public static final int[] IP = new int[] {2, 6, 3, 1, 4, 8, 5, 7};
    public static final int[] IP_INV = new int[] {4, 1, 3, 5, 7, 2, 8, 6};
    public static final int[] EP = new int[] {4, 1, 2, 3, 2, 3, 4, 1};

    public static boolean[] key;
    public static boolean[] K1 = new boolean[8];
    public static boolean[] K2 = new boolean[8];
    

    public SDES(){

    }

    /**
     * Convert the given byte array to a String
     * @author Kayla Weldon
     * @param inp An array of bytes, hopefully storing the codes of printable characters.
     * @return The characters as a String.
     */
    public String byteArrayToCharString(byte[] inp){
        String result = new String(inp);
        return result;
    
    }

    /*
     * Encrypt the given string using SDES Each character produces a byte of cipher.
     */
    public byte[] encrypt(String msg){
        char[] charArray = msg.toCharArray();
        byte[] encrypted = new byte[msg.length()];
        for (int i = 0; i < charArray.length; i++) {
            encrypted[i] = encryptByte((byte)charArray[i]);
        }
        return encrypted;
    }

    /*
     * todo: Decrypt the given byte array.
     */
    public byte[] decrypt(byte[] cipher){
        byte[] decrypted = new byte[cipher.length];
        for (int i = 0; i < cipher.length; i++) {
            decrypted[i] = decryptByte(cipher[i]);
        }
        return decrypted;
    }

    
    /** Send the bitArray to stdout as 1's and 0's */
    public void show(boolean[] inp){
        StringBuilder builder = new StringBuilder(inp.length);
        for(int i = 0; i < inp.length; i++) {
            // Convert true/false to 1/0 and append
            builder.append(inp[i] ? '1' : '0');
            // Break it up into groups of 8
            if(i % 8 == 7) {
                builder.append(' ');
            }
        }
        System.out.println(builder);
    }

    /**
     * Send the byteArray to stdout
     */
    public void show(byte[] byteArray){
        StringBuilder builder = new StringBuilder(byteArray.length * Byte.SIZE);
        for (byte b : byteArray) {
            for(int i = 0; i < Byte.SIZE; i++) {
                // Check each bit by shifting a mask over
                builder.append((b & (1 << i)) == 0 ? '0' : '1');
            }
            builder.append(' ');
        }
        System.out.println(builder);
    }

    /*
    * todo: Get a 10 bit key from the keyboard, such as 1010101010. Store it as an array of booleans in a field.
    */
    public void getKey10(Scanner scanner){
        
    }
}
