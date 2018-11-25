package Algorithm;

/**
 * 04.10.2018 | created by Lukas S
 */

public class Message {
    private int[] data;
    private int[][] dataSplit;
    private int blockLength;

    public Message(String hex) {
        this(hex, 64);
    }

    public Message(String hex, int blockLength) {
        this(Bits.toBits(hex, blockLength), blockLength);
    }

    public Message(int[] data) {
        this(data, 64);
    }

    public Message(int[] data, int blockLength) {
        this.blockLength = blockLength;
        this.data = data;
        this.data = Bits.expand(data, this.blockLength * getBlockCount());
        this.dataSplit = splitData();
    }

    /**
     * Splits the {@link Message#data} into blocks due to the {@link Message#blockLength}.
     *
     * @return - The split array
     */
    private int[][] splitData() {
        int[][] retVal;

        if (data.length <= blockLength) {
            retVal = new int[1][blockLength];
            System.arraycopy(data, 0, retVal[0], 0, data.length);
        } else {
            int blockCount = getBlockCount();

            retVal = new int[blockCount][blockLength];

            for (int i = 0; i < blockCount; i++) {
                System.arraycopy(data, i * blockLength, retVal[i], Math.max(0, blockLength - (data.length - (i * blockLength))), Math.min(blockLength, data.length - (i * blockLength)));
            }
        }

        return retVal;
    }

    /**
     * Getter for {@link Message#data}.
     *
     * @return - A copy of the {@link Message#data} array.
     */
    public int[] getData() {
        return data.clone();
    }

    /**
     * Getter for {@link Message#dataSplit}.
     *
     * @param pos - Position of the block that should be returned
     * @return - A copy of the block at the specified position
     * @throws IndexOutOfBoundsException - Block position does not exist
     */
    public int[] getBlock(int pos) throws IndexOutOfBoundsException {
        if (pos < 0 || pos >= getBlockCount())
            throw new IndexOutOfBoundsException("Block " + pos + " does not exist!");
        return dataSplit[pos].clone();
    }

    /**
     * Getter for the block count of {@link Message#dataSplit}.
     *
     * @return - Block count
     */
    public int getBlockCount() {
        int blockCount = data.length / blockLength;
        if (data.length % blockLength != 0) blockCount++;
        return blockCount;
    }

    /**
     * Getter for the {@link Message#blockLength}.
     *
     * @return - The {@link Message#blockLength}
     */
    public int getBlockLength() {
        return this.blockLength;
    }

    /**
     * Transforms the {@link Message#data} into a hexadecimal string and an ASCII text.
     *
     * @return - The string
     */
    @Override
    public String toString() {
        String hex = Bits.toHex(data);
        return String.format("Hex: %s | Text: %s", hex, Bits.hexToString(hex));
    }

    private String dataSplitAsString(String spaces) {
        StringBuilder ret = new StringBuilder("dataSplit");

        for (int i = 0; i < dataSplit.length; i++) {
            ret.append("\n  ").append(spaces).append(i).append("  = ").append(Bits.toString(dataSplit[i]));
        }

        return ret.toString();
    }

    public String toString(String spaces) {
        return String.format("%s  toString     = %s\n" +
                             "%s  blockLength  = %s\n" +
                             "%s  data         = %s\n" +
                             "%s  %s",
                spaces, toString(), spaces, blockLength, spaces, Bits.toString(data), spaces, dataSplitAsString(spaces + "  "));
    }
}
