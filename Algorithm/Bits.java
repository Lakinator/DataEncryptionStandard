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
     * Transforms a hexadecimal into a bit array.
     *
     * @param hex    - Hexadecimal that will be transformed
     * @param minLen - The minimum length of the desired bit array, 0's are added to reach the minimum length
     * @return - The bit array
     */
    public static int[] toBits(String hex, int minLen) {
        ArrayList<Integer> resultList = new ArrayList<>();
        BigInteger temp = new BigInteger(hex, 16);

        while (temp.compareTo(new BigInteger("0", 10)) > 0) {
            resultList.add(temp.mod(new BigInteger("2", 10)).intValue());
            temp = temp.divide(new BigInteger("2", 10));
        }

        return finalizedArray(resultList, minLen, true);
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
     * Transforms a bit array into a <code>BigInteger</code>.
     * <p>
     * This is used for big bit arrays.
     *
     * @param bits - The bit array that will be transformed
     * @return - The <code>BigInteger</code>
     * @throws IllegalArgumentException - Bit array may not contain any other values than 0 or 1
     */
    public static BigInteger toBigInt(final int[] bits) throws IllegalArgumentException {
        BigInteger result = new BigInteger("0", 10);
        for (int i = bits.length - 1; i >= 0; i--) {
            if (bits[i] != 0 && bits[i] != 1)
                throw new IllegalArgumentException("Bit array may not contain any other values than 0 or 1!");
            BigInteger b1 = new BigInteger("2").pow((bits.length - 1) - i);
            BigInteger b2 = new BigInteger("" + bits[i]).multiply(b1);
            result = result.add(b2);
        }
        return result;
    }

    /**
     * Transforms a bit array into a hexadecimal string.
     *
     * @param bits - The bit array that will be transformed
     * @return - The String
     */
    public static String toHex(final int[] bits) {
        return toBigInt(bits).toString(16).toUpperCase();
    }

    /**
     * Transforms a string into a hexadecimal value.
     *
     * @param str - The string that will be transformed
     * @return - The string as a hexadecimal value
     */
    public static String stringToHex(String str) {
        ArrayList<Integer> resultList = new ArrayList<>();

        for (char c : str.toCharArray()) {
            for (int i : toBits(c, 8)) {
                resultList.add(i);
            }
        }

        return toHex(finalizedArray(resultList, 0, false));
    }

    /**
     * Counterpart to {@link Bits#stringToHex(String)}.
     *
     * @param hex - The hexadecimal value that will be transformed
     * @return - The hexadecimal value as a string
     */
    @Deprecated
    public static String hexToString(String hex) {
        StringBuilder sb = new StringBuilder();
        int[] temp = new int[8];
        int[] bits = Bits.toBits(hex, 8);

        for (int i = 0; i < bits.length / 8; i++) {
            System.arraycopy(bits, i * 8, temp, 0, 8);
            sb.append((char) toDecimal(temp));
            //Maybe remove this => Experimental: Every ASCII = 0 will be removed
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
