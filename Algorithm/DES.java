package Algorithm;

import Observer.Blockdata;
import Observer.Observer;
import Observer.Rounddata;
import com.sun.istack.internal.Nullable;

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
    public Message encrypt(Message msg, Key key, @Nullable Observer observer) {
        key.generateSubkeys(16);

        int[] encrypted = new int[msg.getBlockCount() * msg.getBlockLength()];

        //EBC Mode => TODO CBC Mode
        for (int i = 0; i < msg.getBlockCount(); i++) {
            int[] temp = cipher(msg.getBlock(i), key, true, observer, i);
            System.arraycopy(temp, 0, encrypted, i * msg.getBlockLength(), temp.length);
        }

        Message m = new Message(encrypted);
        if (observer != null) {
            observer.setBlock_mode(this.mode);
            observer.setMsgEnd(m);
        }
        return m;
    }

    /**
     * Decrypts the given message with the given key.
     *
     * @param msg - The message
     * @param key - The key
     * @return - A message containing the decrypted data
     */
    public Message decrypt(Message msg, Key key, @Nullable Observer observer) {
        key.generateSubkeys(16);

        int[] decrypted = new int[msg.getBlockCount() * msg.getBlockLength()];

        //EBC Mode => TODO CBC Mode
        for (int i = 0; i < msg.getBlockCount(); i++) {
            int[] temp = cipher(msg.getBlock(i), key, false, observer, i);
            System.arraycopy(temp, 0, decrypted, i * msg.getBlockLength(), temp.length);
        }

        Message m = new Message(decrypted);
        if (observer != null) {
            observer.setBlock_mode(this.mode);
            observer.setMsgEnd(m);
        }
        return m;
    }

    /**
     * The main part of the Observer Encryption Standard.
     *
     * @param block   - A 64 bit block
     * @param key     - The key
     * @param encrypt - True for encryption, false for decryption
     * @return - A bit array containing the encrypted/decrypted block
     */
    private int[] cipher(int[] block, Key key, boolean encrypt, @Nullable Observer observer, int blockIndex) {
        int[][] parts = new int[2][32]; //left and right

        Blockdata blockdata = new Blockdata(blockIndex, new Message(block.clone()));
        blockdata.setKey(key);

        //Init
        block = Permutation.IP(block);
        blockdata.setIP(block.clone());

        System.arraycopy(block, 0, parts[0], 0, 32); //left
        System.arraycopy(block, 32, parts[1], 0, 32); //right

        //Feistel
        int[] rightOld, retVal;

        Rounddata rounddata;

        for (int i = 0; i < 16; i++) {
            rounddata = new Rounddata();
            rounddata.setRound(i + 1);
            rounddata.setPartL_Start(parts[0].clone());
            rounddata.setPartR_Start(parts[1].clone());

            rightOld = parts[1];

            if (encrypt) {
                parts[1] = F(key.getSubkey(i + 1), parts[1], rounddata);
            } else
                parts[1] = F(key.getSubkey(16 - i), parts[1], rounddata);

            parts[1] = Bits.xor(parts[1], parts[0]);

            parts[0] = rightOld;

            rounddata.setPartL_End(parts[0].clone());
            rounddata.setPartR_End(parts[1].clone());
            blockdata.setRounddata(rounddata);
        }

        retVal = new int[64];
        System.arraycopy(parts[1], 0, retVal, 0, 32);
        System.arraycopy(parts[0], 0, retVal, 32, 32);

        retVal = Permutation.IP_Inversed(retVal);
        blockdata.setIP_Inv(retVal.clone());
        if (observer != null) observer.setBlockdata(blockdata);
        return retVal;
    }

    /**
     * The inner function F of the Feistel-Cipher
     *
     * @param subkey - Subkey from the corresponding round
     * @param part   - The part that should be ciphered
     * @return - The new array
     */
    private int[] F(int[] subkey, int[] part, Rounddata rounddata) {
        int[] retVal;

        int[][] data = new int[5][];
        data[0] = part.clone();

        retVal = Expansion.expand(part);
        data[1] = retVal.clone();
        retVal = Bits.xor(retVal, subkey);
        data[2] = retVal.clone();
        retVal = SBoxes.encrypt(retVal);
        data[3] = retVal.clone();
        retVal = Permutation.P(retVal);
        data[4] = retVal.clone();

        rounddata.setSubkey(subkey.clone());
        rounddata.setFData(data[0], data[1], data[2], data[3], data[4]);

        return retVal;
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
