package Observer;

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
}
