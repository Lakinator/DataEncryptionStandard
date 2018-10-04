package Algorithm;


/**
 * 09.03.2018 | created by Lukas S
 */

public class DES {
    private BLOCK_MODE mode;

    public enum BLOCK_MODE {
        EBC, CBC
    }

    public DES() {
        this(BLOCK_MODE.EBC);
    }

    public DES(BLOCK_MODE mode) {
        this.mode = mode;
    }

    /**
     * Encrypts the given message with the given key.
     *
     * @param msg - The message
     * @param key - The key
     * @return - A message containing the encrypted data
     */
    public Message encrypt(Message msg, Key key) {
        key.generateSubkeys(16);

        int[] encrypted = new int[msg.getBlockCount() * msg.getBlockLength()];

        //EBC Mode => TODO CBC Mode
        for (int i = 0; i < msg.getBlockCount(); i++) {
            int[] temp = cipher(msg.getBlock(i), key, true);
            System.arraycopy(temp, 0, encrypted, i * msg.getBlockLength(), temp.length);
        }

        return new Message(Bits.toString(encrypted));
    }

    /**
     * Decrypts the given message with the given key.
     *
     * @param msg - The message
     * @param key - The key
     * @return - A message containing the decrypted data
     */
    public Message decrypt(Message msg, Key key) {
        key.generateSubkeys(16);

        int[] decrypted = new int[msg.getBlockCount() * msg.getBlockLength()];

        //EBC Mode => TODO CBC Mode
        for (int i = 0; i < msg.getBlockCount(); i++) {
            int[] temp = cipher(msg.getBlock(i), key, false);
            System.arraycopy(temp, 0, decrypted, i * msg.getBlockLength(), temp.length);
        }

        return new Message(Bits.toString(decrypted));
    }

    /**
     * The main part of the Data Encryption Standard.
     *
     * @param block   - A 64 bit block
     * @param key     - The key
     * @param encrypt - True for encryption, false for decryption
     * @return - A bit array containing the encrypted/decrypted block
     */
    private int[] cipher(int[] block, Key key, boolean encrypt) {
        int[][] parts = new int[2][32]; //left and right

        //Init
        block = Permutation.init_permute(block);

        System.arraycopy(block, 0, parts[0], 0, 32); //left
        System.arraycopy(block, 32, parts[1], 0, 32); //right

        //Feistel
        int[] rightOld, retVal;

        for (int i = 0; i < 16; i++) {
            rightOld = parts[1];

            //F-Function
            parts[1] = Expansion.expand(parts[1]);
            if (encrypt) parts[1] = Bits.xor(parts[1], key.getSubkey(i + 1));
            else parts[1] = Bits.xor(parts[1], key.getSubkey(16 - i));
            parts[1] = SBoxes.encrypt(parts[1]);
            parts[1] = Permutation.permute(parts[1]);

            parts[1] = Bits.xor(parts[1], parts[0]);

            //Swap
            parts[0] = rightOld;
        }

        retVal = new int[64];
        System.arraycopy(parts[0], 0, retVal, 0, 32);
        System.arraycopy(parts[1], 0, retVal, 32, 32);

        return Permutation.init_permute_inversed(retVal);
    }

    /**
     * Getter for {@link DES#mode}.
     *
     * @return - The {@link DES#mode}
     */
    public BLOCK_MODE getMode() {
        return mode;
    }
}
