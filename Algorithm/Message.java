package Algorithm;

/**
 * 04.10.2018 | created by Lukas S
 */

public class Message {
    private int[] data;
    private int[][] dataSplit;
    private int blockLength;

    public Message(String msg) {
        this(msg, 64);
    }

    public Message(String msg, int blockLength) {
        this.blockLength = blockLength;
        data = Bits.toBits(msg, this.blockLength);
        dataSplit = splitData();
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
            int blockCount = data.length / blockLength;
            if (data.length % blockLength != 0) blockCount++;

            retVal = new int[blockCount][blockLength];

            for (int i = 0; i < blockCount; i++) {
                System.arraycopy(data, i * blockLength, retVal[i], 0, Math.min(blockLength, data.length - (i * blockLength)));
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
        return this.dataSplit.length;
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
     * Transforms the {@link Message#data} into a string.
     *
     * @return - The string
     */
    @Override
    public String toString() {
        return Bits.toString(data);
    }
}
