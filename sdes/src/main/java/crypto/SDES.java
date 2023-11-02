package crypto;

import java.util.Scanner;
import static crypto.ByteHandler.*;
import static crypto.Permutation.expPerm;

public class SDES {
    public static final int[] P10 = new int[] {2, 4, 1, 6, 3, 9, 0, 8, 7, 5};
    public static final int[] P8 = new int[] {5, 2, 6, 3, 7, 4, 9, 8};
    public static final int[] IP = new int[] {1, 5, 2, 0, 3, 7, 4, 6};
    public static final int[] IP_INV = new int[] {3, 0, 2, 4, 6, 1, 7, 5};
    public static final int[] EP = new int[] {3, 0, 1, 2, 1, 2, 3, 0};

    public static final int[] P_K0 = new int[] {0, 6, 8, 3, 7, 2, 9, 5};
    public static final int[] P_K1 = new int[] {7, 2, 5, 4, 9, 1, 8, 0};

    public static boolean[] key = new boolean[10];
    public static boolean[] K0 = new boolean[8];
    public static boolean[] K1 = new boolean[8];
    

    public SDES(){
        getKey10(new Scanner(System.in));
        K0 = expPerm(key, P_K0);
        K1 = expPerm(key, P_K1);
    }

    /**
     * Convert the given byte array to a String
     * @author Kayla Weldon
     * @param inp An array of bytes, hopefully storing the codes of printable characters.
     * @return The characters as a String.
     */
    public String byteArrayToCharString(byte[] inp){
        return new String(inp);
    
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
    public static void getKey10(Scanner scanner) {
        String keyInput = "";
        boolean valid = false;
        
        while (!valid) { 
            System.out.println("Enter a 10-bit key: ");
            keyInput = scanner.nextLine();

            String test = keyInput.replace("0", "");
            test = test.replace("1", "");

            if (test.length() == 0 && keyInput.length() == 10) {
                valid = true;
                break;
            }

            System.out.println("Key must be 10 bits of 1s and 0s.");
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
