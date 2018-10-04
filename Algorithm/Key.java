package Algorithm;

/**
 * 30.09.2018 | created by Lukas S
 */

public class Key {
    private final static int[] permutedLeft_PC1 = new int[]{
            57, 49, 41, 33, 25, 17, 9,
            1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27,
            19, 11, 3, 60, 52, 44, 36
    };

    private final static int[] permutedRight_PC1 = new int[]{
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29,
            21, 13, 5, 28, 20, 12, 4
    };

    private final static int[][] permutedArray_PC1 = new int[][]{permutedLeft_PC1, permutedRight_PC1};

    private final static int[] permuted_PC2 = new int[]{
            14, 17, 11, 24, 1, 5, 3, 28,
            15, 6, 21, 10, 23, 19, 12, 4,
            26, 8, 16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55, 30, 40,
            51, 45, 33, 48, 44, 49, 39, 56,
            34, 53, 46, 42, 50, 36, 29, 32
    };

    private final static int[][] shifts = new int[][]{
            {1, 2, 9, 16}, /* 1 shift */
            {3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15} /* 2 shifts */
    };

    private int currentRound, maxRound;
    private int[][] bits;
    private int[][] subkeys;


    public Key(final int[] key) throws IllegalArgumentException {
        if (!verifyKey(key)) return;

        maxRound = -1;
        currentRound = 0;

        //Permutation
        PC1(key);
    }

    /**
     * This method generates all subkeys until the {@link #maxRound} is reached.
     *
     * @param maxRound - Round limiter
     */
    public void generateSubkeys(int maxRound) {
        if (maxRound <= 0 || this.maxRound == maxRound) return;

        this.maxRound = maxRound;
        subkeys = new int[this.maxRound][48];

        for (int i = 0; i < this.maxRound; i++) {
            subkeys[i] = getNextKey();
        }
    }

    /**
     * Calculates one subkey due to the {@link Key#currentRound}.
     * Every subkey depends on the last round that has been calculated.
     *
     * @return - The subkey
     */
    private int[] getNextKey() {
        currentRound++;
        for (int[] bit : bits) {
            circularLeftShift(bit, getShifts());
        }
        return PC2();
    }

    /**
     * This "Permuted-Choice" method divides the input bit array while also leaving out
     * the "Parity-Bits" every 8th bit.
     * The result is stored in {@link Key#bits}.
     * Uses {@link Key#permutedArray_PC1} which contains {@link Key#permutedLeft_PC1} and {@link Key#permutedRight_PC1}.
     *
     * @param input - The bit arry that will be permuted
     */
    private void PC1(final int[] input) {
        //Init
        bits = new int[2][];
        for (int i = 0; i < bits.length; i++) bits[i] = new int[28];

        //Permuting
        for (int i = 0; i < bits.length; i++) {
            for (int j = 0; j < bits[i].length; j++) {
                bits[i][j] = input[permutedArray_PC1[i][j] - 1];
            }
        }
    }

    /**
     * This "Permuted-Choice" method merges and cuts down the two
     * bit arrays in {@link Key#bits} to a 48 bit array.
     * Uses {@link Key#permuted_PC2}.
     *
     * @return - The merged and cut down bit array
     */
    private int[] PC2() {
        int[] retVal = new int[48];

        for (int i = 0; i < retVal.length; i++) {
            if (permuted_PC2[i] <= 28) retVal[i] = bits[0][permuted_PC2[i] - 1];
            else if (permuted_PC2[i] > 28) retVal[i] = bits[1][(permuted_PC2[i] - 28) - 1];
        }

        return retVal;
    }

    /**
     * Calulates the number of shifts that have to be made due to the {@link Key#currentRound}.
     * This depends on {@link Key#shifts}.
     *
     * @return - Number of shifts
     */
    private int getShifts() {
        for (int i = 0; i < shifts.length; i++) {
            for (int j = 0; j < shifts[i].length; j++) {
                if (shifts[i][j] == currentRound) return (i + 1);
            }
        }
        return 0;
    }

    /**
     * Left shifts a bit <code>n</code> times (circular).
     *
     * @param bits - The bit array that will be shifted
     * @param n    - The number of times the bit array should be shifted
     */
    private void circularLeftShift(int[] bits, int n) {
        int temp = bits[0];
        for (int i = 0; i < n; i++) {
            System.arraycopy(bits, 1, bits, 0, bits.length - 1);
            bits[bits.length - 1] = temp;
        }
    }

    /**
     * Checks the given bit key array for "Parity-Bits" which means that
     * every 8th bit should be the cross sum of the previous 7 bits modulo 2.
     *
     * @param key - The bit array that will be verified
     * @return - True if the given bit array is a proper key
     * @throws IllegalArgumentException - Key has to be exactly 64 indices long | Wrong "Parity-Bit"
     */
    private boolean verifyKey(final int[] key) throws IllegalArgumentException {
        if (key.length != 64) throw new IllegalArgumentException("Key has to be exactly 64 indices long!");

        for (int i = 1; i < 9; i++) {
            int pos = (i * 8) - 1;
            int sum = 0;

            //Cross sum of 7 of 8 bits from one byte
            for (int j = pos - 1; j > pos - 8; j--) {
                sum += key[j];
            }

            //Check for parity bit
            if (key[pos] != (sum + 1) % 2) {
                throw new IllegalArgumentException(String.format("Wrong parity bit (%s) at array index %s (Byte %s) | Sum was %s | Expected parity bit is %s", key[pos], pos, i, sum, ((sum + 1) % 2)));
            }
        }

        return true;
    }

    /**
     * Returns a copy from the subkey of the specified round.
     *
     * @param round - The round from which the subkey should be (starting with 1)
     * @return - The subkey
     * @throws IndexOutOfBoundsException - Subkey round does not exist
     */
    public int[] getSubkey(int round) throws IndexOutOfBoundsException {
        if (round < 1 || round > maxRound)
            throw new IndexOutOfBoundsException("Subkey round " + round + " does not exist!");
        return subkeys[round - 1].clone();
    }
}
