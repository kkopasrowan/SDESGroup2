package crypto;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args)
    {       
        crypto.SDES sdes = new crypto.SDES();

        Scanner scanner = new Scanner (System.in);

        System.out.println ("Enter plain text, or hit 'Enter' to terminate");
        String plain = scanner.nextLine();
        byte [] cipher;
        while (!plain.isEmpty()){
            cipher = sdes.encrypt  (plain);
            System.out.print ("Cipher is ");
            sdes.show(cipher);
            System.out.println (sdes.byteArrayToCharString (sdes.decrypt (cipher)));
            System.out.println ("Enter plain text, or hit 'Enter' to terminate");
            plain = scanner.nextLine();
        }
    }
}