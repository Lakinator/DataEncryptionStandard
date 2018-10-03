package Algorithm;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 09.03.2018 | created by Lukas S
 */

public class Bits {

    /**
     * Transforms a decimal into a bit array.
     *
     * @param dec_number - The number that will be transformed
     * @param minLen     - The minimum length of the desired bit array, 0's are added to reach the minimum length
     * @return - The bit array
     */
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

    /**
     * Transforms BigInteger into a bit array.
     *
     * @param hex    - Hex value that will be transformed
     * @param minLen - The minimum length of the desired bit array, 0's are added to reach the minimum length
     * @return - The bit array
     */
    public static int[] hex_to_bits(BigInteger hex, int minLen) {
        ArrayList<Integer> resultList = new ArrayList<>();
        int[] resultArray;

        while (hex.compareTo(new BigInteger("0", 10)) > 0) {
            resultList.add(hex.mod(new BigInteger("2", 10)).intValue());
            hex = hex.divide(new BigInteger("2", 10));
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

    /**
     * Transforms a bit array into a decimal.
     *
     * @param bits - The bit array that will be transformed
     * @return - The decimal
     * @throws IllegalArgumentException - Bit array may not contain any other values than 0 or 1
     */
    public static int bits_to_decimal(final int[] bits) throws IllegalArgumentException {
        int result = 0;
        for (int i = bits.length - 1; i >= 0; i--) {
            if (bits[i] != 0 && bits[i] != 1)
                throw new IllegalArgumentException("Bit array may not contain any other values than 0 or 1!");
            result += bits[i] * ((int) Math.pow(2, (bits.length - 1) - i));
        }
        return result;
    }

    /**
     * Merges two bit arrays into one by using the xor rules.
     *
     * @param bits      - The first bit array
     * @param otherBits - The second bit array
     * @return - The merged bit array
     * @throws IllegalArgumentException - Bit arrays must be of the same size
     */
    public static int[] xor(final int[] bits, final int[] otherBits) throws IllegalArgumentException {
        if (bits.length != otherBits.length) throw new IllegalArgumentException("Bit arrays must be of the same size!");

        int[] retVal = new int[bits.length];

        for (int i = 0; i < bits.length; i++) {
            retVal[i] = (bits[i] + otherBits[i]) % 2;
        }

        return retVal;
    }
}
