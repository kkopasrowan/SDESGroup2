package crypto;

import java.util.Scanner;
import static crypto.ByteHandler.*;

public class SDES {
    public static final int[] P10 = new int[] {3, 5, 2, 7, 4, 10, 1, 9, 8, 6};
    public static final int[] IP = new int[] {2, 6, 3, 1, 4, 8, 5, 7};
    public static final int[] IP_INV = new int[] {4, 1, 3, 5, 7, 2, 8, 6};
    public static final int[] EP = new int[] {4, 1, 2, 3, 2, 3, 4, 1};

    public static boolean[] key = new boolean[10];
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

    /**
     * Encrypt the given string using SDES Each character produces a byte of cipher.
     * @author James Blake
     */
    public byte[] encrypt(String msg){
        byte[] plaintext = msg.getBytes();
        byte[] encrypted = new byte[msg.length()];
        for (int i = 0; i < plaintext.length; i++) {
            encrypted[i] = encryptByte(plaintext[i]);
        }
        return encrypted;
    }

    /**
     * Decrypt the given byte array.
     * @author James Blake
     */
    public byte[] decrypt(byte[] cipher){
        byte[] decrypted = new byte[cipher.length];
        for (int i = 0; i < cipher.length; i++) {
            decrypted[i] = decryptByte(cipher[i]);
        }
        return decrypted;
    }

    
    /**
     * Send the bitArray to stdout as 1's and 0's
     * @author James Blake
     */
    public static void show(boolean[] inp){
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
     * @author James Blake
     */
    public static void show(byte[] byteArray) {
        StringBuilder builder = new StringBuilder(byteArray.length);
        for (byte b : byteArray) {
            builder.append(b).append(' ');
        }
        System.out.println(builder);
    }

    /**
     * Get a 10 bit key from the keyboard, such as 1010101010. 
     * Stores it as an array of booleans in a field.
     * @author Kayla Weldon
     * @param scanner scanner to read keyboard input
     */
    public void getKey10(Scanner scanner) {
        System.out.println("Enter a 10-bit key: ");
        String keyInput = scanner.nextLine();

        boolean valid = false;

        while (!valid) { 
            System.out.println("Key must be 10 bits of 1s and 0s. Enter again:");
            keyInput = scanner.nextLine();

            String test = keyInput.replace("0", "");
            test = keyInput.replace("1", "");

            if (test.length() == 0) {
                valid = true;
            }
        }

        for (int i = 0; i < 10; i++) {
            char bitChar = keyInput.charAt(i);
            if (bitChar == '0') {
                key[i] = false;
            } else {
                key[i] = true;
            }
        }
    }
}
