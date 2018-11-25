package Observer;

import Algorithm.Bits;

/**
 * 11.11.2018 | created by Lukas S
 */

public class Rounddata {
    private int round;
    private int[] subkey, partL_Start, partR_Start, partL_End, partR_End;
    private FData fData;

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int[] getSubkey() {
        return subkey;
    }

    public void setSubkey(int[] subkey) {
        this.subkey = subkey;
    }

    public int[] getPartL_Start() {
        return partL_Start;
    }

    public void setPartL_Start(int[] partL_Start) {
        this.partL_Start = partL_Start;
    }

    public int[] getPartR_Start() {
        return partR_Start;
    }

    public void setPartR_Start(int[] partR_Start) {
        this.partR_Start = partR_Start;
    }

    public int[] getPartL_End() {
        return partL_End;
    }

    public void setPartL_End(int[] partL_End) {
        this.partL_End = partL_End;
    }

    public int[] getPartR_End() {
        return partR_End;
    }

    public void setPartR_End(int[] partR_End) {
        this.partR_End = partR_End;
    }

    public FData getFData() {
        return fData;
    }

    public void setFData(int[] start, int[] expansion, int[] xor, int[] sboxes, int[] permutation) {
        this.fData = new FData(start, expansion, xor, sboxes, permutation);
    }

    public String toString(String spaces) {
        return String.format("%s  round        = %s\n" +
                             "%s  subkey       = %s\n" +
                             "%s  partL_Start  = %s\n" +
                             "%s  partR_Start  = %s\n" +
                             "%s  partL_End    = %s\n" +
                             "%s  partR_End    = %s\n" +
                             "%s  %s",
                spaces, round, spaces, Bits.toString(subkey), spaces, Bits.toString(partL_Start), spaces, Bits.toString(partR_Start), spaces, Bits.toString(partL_End), spaces, Bits.toString(partR_End), spaces, fData.toString(spaces + "  "));
    }
}
