import Algorithm.Bits;
import Algorithm.DES;
import Algorithm.Key;
import Algorithm.Message;

import java.util.Arrays;

/**
 * 03.10.2018 | created by Lukas S
 */

public class Main {
    public static void main(String[] args) {

        Message msg = new Message(Bits.stringToHex("Hallo Welt!"));

        System.out.println("Data: " + msg.toString());
        System.out.println(Arrays.toString(msg.getData()));
        for (int i = 0; i < msg.getBlockCount(); i++) {
            System.out.println(Arrays.toString(msg.getBlock(i)));
        }

        Key key = new Key("6D62B676B5F7B932");

        //Encrypt
        DES des = new DES();
        Message enc = des.encrypt(msg, key);
        System.out.println("Encoded: " + enc.toString());
        System.out.println(Arrays.toString(enc.getData()));

        //Decrypt
        Message dec = des.decrypt(enc, key);
        System.out.println("Decoded: " + dec.toString());
        System.out.println(Arrays.toString(dec.getData()));
    }
}
