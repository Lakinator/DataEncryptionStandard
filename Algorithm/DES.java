package Algorithm;


/**
 * 09.03.2018 | created by Lukas S
 */

public class DES {

    public enum BLOCK_MODE {
        EBC, CBC
    }

    public DES() {

    }

    public void encrypt() {

    }

    public void decrypt() {

    }

    public int[] run(int[] block, Key key) {
        int[][] parts = new int[2][32]; //left and right

        //Init
        block = Permutation.init_permute(block);

        System.arraycopy(block, 0, parts[0], 0, 32); //left
        System.arraycopy(block, 32, parts[1], 0, 32); //right

        //Feistel
        int[] leftOld, rightOld, temp, retVal;

        for (int i = 0; i < 16; i++) {
            leftOld = parts[0];
            rightOld = parts[1];

            //F-Function
            temp = Expansion.expand(rightOld);
            temp = Bits.xor(temp, key.getNextKey());
            temp = SBoxes.encrypt(temp);
            temp = Permutation.permute(temp);

            temp = Bits.xor(temp, leftOld);

            //Next round
            parts[0] = rightOld;
            parts[1] = temp;
        }

        retVal = new int[64];
        System.arraycopy(parts[0], 0, retVal, 0, 32);
        System.arraycopy(parts[1], 0, retVal, 32, 32);

        return Permutation.init_permute_inversed(retVal);
    }
}
