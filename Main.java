import Algorithm.DES;
import Algorithm.Key;
import Algorithm.Message;

import java.util.Arrays;

/**
 * 03.10.2018 | created by Lukas S
 */

public class Main {
    public static void main(String[] args) {

        int[] data = new int[]{
                0, 0, 0, 0, 0, 0, 0, 1,
                0, 0, 1, 0, 0, 0, 1, 1,
                0, 1, 0, 0, 0, 1, 0, 1,
                0, 1, 1, 0, 0, 1, 1, 1,
                1, 0, 0, 0, 1, 0, 0, 1,
                1, 0, 1, 0, 1, 0, 1, 1,
                1, 1, 0, 0, 1, 1, 0, 1,
                1, 1, 1, 0, 1, 1, 1, 1
        };

        int[] keyBook = new int[]{
                0, 0, 0, 1, 0, 0, 1, 1,
                0, 0, 1, 1, 0, 1, 0, 0,
                0, 1, 0, 1, 0, 1, 1, 1,
                0, 1, 1, 1, 1, 0, 0, 1,
                1, 0, 0, 1, 1, 0, 1, 1,
                1, 0, 1, 1, 1, 1, 0, 0,
                1, 1, 0, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 0, 0, 0, 1
        };

        int[] keyArr = new int[]{
                0, 1, 1, 0, 1, 1, 0, 1,
                0, 1, 1, 0, 0, 0, 1, 0,
                1, 0, 1, 1, 0, 1, 1, 0,
                0, 1, 1, 1, 0, 1, 1, 0,
                1, 0, 1, 1, 0, 1, 0, 1,
                1, 1, 1, 1, 0, 1, 1, 1,
                1, 0, 1, 1, 1, 0, 0, 1,
                0, 0, 1, 1, 0, 0, 1, 0
        };

        Message msg = new Message(data);
        System.out.println("Data");
        System.out.println(msg.toString());
        for (int i = 0; i < msg.getBlockCount(); i++) {
            System.out.println(Arrays.toString(msg.getBlock(i)));
        }

        Key key = new Key(keyBook);

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
