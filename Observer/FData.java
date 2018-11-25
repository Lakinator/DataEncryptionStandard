package Observer;

import Algorithm.Bits;

/**
 * 11.11.2018 | created by Lukas S
 */

public class FData {
    private int[] start, expansion, xor, sboxes, permutation;

    public FData(int[] start, int[] expansion, int[] xor, int[] sboxes, int[] permutation) {
        this.start = start;
        this.expansion = expansion;
        this.xor = xor;
        this.sboxes = sboxes;
        this.permutation = permutation;
    }

    public int[] getStart() {
        return start;
    }

    public int[] getExpansion() {
        return expansion;
    }

    public int[] getXor() {
        return xor;
    }

    public int[] getSboxes() {
        return sboxes;
    }

    public int[] getPermutation() {
        return permutation;
    }

    public String toString(String spaces) {
        return String.format("fData\n" +
                             "%s  start        = %s\n" +
                             "%s  expansion    = %s\n" +
                             "%s  xor          = %s\n" +
                             "%s  sboxes       = %s\n" +
                             "%s  permutation  = %s",
                spaces, Bits.toString(start), spaces, Bits.toString(expansion), spaces, Bits.toString(xor), spaces, Bits.toString(sboxes), spaces, Bits.toString(permutation));
    }
}
