import Algorithm.*;
import Observer.Observer;

import java.util.Arrays;

/**
 * 03.10.2018 | created by Lukas S
 */

public class Main {
    public static void main(String[] args) {

        String hex = Bits.stringToHex("HelloDES");
        Message msg = new Message(hex);

        System.out.println("Message { " + msg.toString() + " }");
        System.out.println(Arrays.toString(msg.getData()));
        for (int i = 0; i < msg.getBlockCount(); i++) {
            System.out.println(Arrays.toString(msg.getBlock(i)));
        }

        Key key = new Key("6D62B676B5F7B932");

        //Observer
        Observer observer = new Observer(msg);

        //Encrypt
        DES des = new DES();
        Message enc = des.encrypt(msg, key, observer);
        System.out.println("Encoded { " + enc.toString() + " }");
        for (int i = 0; i < enc.getBlockCount(); i++) {
            System.out.println(Arrays.toString(enc.getBlock(i)));
        }

        //Decrypt
        Message dec = des.decrypt(enc, key, null);
        System.out.println("Decoded { " + dec.toString() + " }");
        for (int i = 0; i < dec.getBlockCount(); i++) {
            System.out.println(Arrays.toString(dec.getBlock(i)));
        }

    }
}
