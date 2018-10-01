package Algorithm;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 09.03.2018 | created by Lukas S
 */

public class Bits {

    public static int[] decimal_to_bits(int dec_number, int minLen) {
        ArrayList<Integer> resultList = new ArrayList<>();
        int[] resultArray;

        while (dec_number > 0) {
            resultList.add(dec_number % 2);
            dec_number /= 2;
        }

        while (resultList.size() < minLen) {
            resultList.add(0);
        }

        Collections.reverse(resultList);

        resultArray = new int[resultList.size()];
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = resultList.get(i);
        }

        return resultArray;
    }

    public static int bits_to_decimal(final int[] bits) {
        int result = 0;
        for (int i = bits.length - 1; i >= 0; i--) {
            if (bits[i] != 0 && bits[i] != 1) throw new IllegalArgumentException("Bit array may not contain any other values than 0 or 1!");
            result += bits[i] * ((int) Math.pow(2, (bits.length - 1) - i));
        }
        return result;
    }
}
