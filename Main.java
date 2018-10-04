import Algorithm.DES;
import Algorithm.Key;
import Algorithm.Message;

import java.util.Arrays;

/**
 * 03.10.2018 | created by Lukas S
 */

public class Main {
    public static void main(String[] args) {

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

        Message msg = new Message("Hallo Welt");

        Key key = new Key(keyArr);

        //Encrypt
        DES des = new DES();
        Message enc = des.encrypt(msg, key);
        System.out.println(enc.toString());
        System.out.println(Arrays.toString(enc.getData()));

        //Decrypt
        Message dec = des.decrypt(enc, key);
        System.out.println(dec.toString());
        System.out.println(Arrays.toString(dec.getData()));
    }
}
