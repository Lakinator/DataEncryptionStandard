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
     * @param dec    - Decimal that will be transformed
     * @param minLen - The minimum length of the desired bit array, 0's are added to reach the minimum length
     * @return - The bit array
     */
    public static int[] toBits(int dec, int minLen) {
        ArrayList<Integer> resultList = new ArrayList<>();

        while (dec > 0) {
            resultList.add(dec % 2);
            dec /= 2;
        }

        return finalizedArray(resultList, minLen, true);
    }

    /**
     * Transforms a decimal into a bit array.
     *
     * @param hex    - Hex number that will be transformed
     * @param minLen - The minimum length of the desired bit array, 0's are added to reach the minimum length
     * @return - The bit array
     */
    public static int[] toBits(BigInteger hex, int minLen) {
        ArrayList<Integer> resultList = new ArrayList<>();

        while (hex.compareTo(new BigInteger("0", 10)) > 0) {
            resultList.add(hex.mod(new BigInteger("2", 10)).intValue());
            hex = hex.divide(new BigInteger("2", 10));
        }

        return finalizedArray(resultList, minLen, true);
    }

    /**
     * Transforms a string into a bit array.
     *
     * @param str    - String that will be transformed
     * @param minLen - The minimum length of the desired bit array, 0's are added to reach the minimum length
     * @return - The bit array
     */
    public static int[] toBits(String str, int minLen) {
        ArrayList<Integer> resultList = new ArrayList<>();

        for (char c : str.toCharArray()) {
            for (int i : toBits(c, 8)) {
                resultList.add(i);
            }
        }

        return finalizedArray(resultList, minLen, false);
    }

    /**
     * This method turns an ArrayList from any toBits method into a
     * static finalized array to avoid duplicate code.
     *
     * @param resultList - List containing bits
     * @param minLen     - The minimum length of the desired bit array, 0's are added to reach the minimum length
     * @param reverse    -  Determines if the finalized array will be reversed or not
     * @return - The bit array
     */
    private static int[] finalizedArray(ArrayList<Integer> resultList, int minLen, boolean reverse) {
        int[] resultArray;
        while (resultList.size() < minLen) {
            if (reverse) resultList.add(0);
            else resultList.add(0, 0);
        }

        if (reverse) Collections.reverse(resultList);

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
    public static int toDecimal(final int[] bits) throws IllegalArgumentException {
        int result = 0;
        for (int i = bits.length - 1; i >= 0; i--) {
            if (bits[i] != 0 && bits[i] != 1)
                throw new IllegalArgumentException("Bit array may not contain any other values than 0 or 1!");
            result += bits[i] * ((int) Math.pow(2, (bits.length - 1) - i));
        }
        return result;
    }

    /**
     * Transforms a bit array into a string.
     *
     * @param bits - The bit array that will be transformed
     * @return - The decimal
     */
    public static String toString(final int[] bits) throws IllegalArgumentException {
        if (bits.length % 8 != 0) throw new IllegalArgumentException("Invalid bit array!");

        StringBuilder sb = new StringBuilder();
        int[] temp = new int[8];

        for (int i = 0; i < bits.length / 8; i++) {
            System.arraycopy(bits, i * 8, temp, 0, 8);
            sb.append((char) toDecimal(temp));
            //TODO Maybe remove this => Experimental: Every ASCII = 0 will be removed
            if (sb.indexOf("" + (char) 0) > -1) sb.deleteCharAt(sb.indexOf("" + (char) 0));
        }

        return sb.toString();
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
