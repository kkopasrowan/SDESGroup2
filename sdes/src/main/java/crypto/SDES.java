package crypto;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;


public class SDES {

    public SDES(){

    }

    /*
     * todo: Convert the given byte array to a String
     */
    public String byteArrayToCharString(byte[] inp){
        String result = new String(inp);
        return result;
    
    }

    /*
     * Encrypt the given string using SDES Each character produces a byte of cipher.
     */
    public byte[] encrypt(String msg){
        return null; 
    }

    /*
     * todo: Decrypt the given byte array.
     */
    public byte[] decrypt(byte[] cipher){
        return null; 
    }

    
    /*
     * todo: Send the bitArray to stdout as 1's and 0's
     */
    public void show(boolean[] inp){
    }

    //todo: Send the byteArray to stdout
    public void show(byte[] byteArray){
    }

    /*
    * todo: Get a 10 bit key from the keyboard, such as 1010101010. Store it as an array of booleans in a field.
    */
    public void getKey10(Scanner scanner){
        
    }

    

}
