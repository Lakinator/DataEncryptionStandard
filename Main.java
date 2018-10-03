import Algorithm.Bits;
import Algorithm.DES;
import Algorithm.Key;

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

        Key key = new Key(keyArr);
        key.generateSubkeys(16);

        //Encrypt
        DES des = new DES();
        int[] encrypted = des.run(data, key);
        System.out.println(Arrays.toString(encrypted));

        System.out.println(Arrays.toString(Bits.toBits("Hallo", 64)));
        System.out.println(Bits.toString(new int[]{0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
    }
}
