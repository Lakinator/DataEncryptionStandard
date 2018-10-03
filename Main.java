import Algorithm.Bits;
import Algorithm.DES;
import Algorithm.Key;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 03.10.2018 | created by Lukas S
 */

public class Main {
    public static void main(String[] args) {
        BigInteger hex = new BigInteger("FFFFFFFFFFFFFFFF", 16);
        System.out.println(Arrays.toString(Bits.hex_to_bits(hex, -1)));
        System.out.println(Bits.hex_to_bits(hex, -1).length);
        System.out.println(hex);

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

        //Encrypt
        DES des = new DES();
        int[] encrypted = des.run(data, key);
        System.out.println(Arrays.toString(encrypted));

        //Decrypt
        ArrayList<Integer> resultList = new ArrayList<>();
        int[] resultArray;

        for (int i : keyArr) {
            resultList.add(i);
        }

        Collections.reverse(resultList);

        resultArray = new int[resultList.size()];
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = resultList.get(i);
        }

        key = new Key(resultArray);
        System.out.println(Arrays.toString(data));
        System.out.println(Arrays.toString(des.run(encrypted, key)));
    }
}
