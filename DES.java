import Algorithm.*;

import java.util.Arrays;


/**
 * 09.03.2018 | created by Lukas S
 */

public class DES {
    public static void main(String[] args) {

        int[] data = new int[]{
                0, 0, 0, 0, 0, 0, 0, 1,
                0, 0, 1, 0, 0, 0, 1, 1,
                0, 1, 0, 0, 0, 1, 0, 1,
                0, 1, 1, 0, 0, 1, 1, 1,
                1, 0, 0, 0, 1, 0, 0, 1,
                1, 0, 1, 0, 1, 0, 1, 1,
                1, 1, 0, 0, 1, 1, 0, 1,
                1, 1, 1, 0, 1, 1, 1, 1
        };

        int[][] parts = new int[2][32]; //left and right


        int[] keyArr = new int[]{
                0, 1, 1, 0, 1, 1, 0, 1,
                0, 1, 1, 0, 0, 0, 1, 0,
                1, 0, 1, 1, 0, 1, 1, 0,
                0, 1, 1, 1, 0, 1, 1, 0,
                1, 0, 1, 1, 0, 1, 0, 1,
                1, 1, 1, 1, 0, 1, 1, 1,
                1, 0, 1, 1, 1, 0, 0, 1,
                0, 0, 1, 1, 0, 0, 1, 0
        };

        Key key = new Key(keyArr);


        //Init
        data = Permutation.init_permute(data);

        System.arraycopy(data, 0, parts[0], 0, 32); //left
        System.arraycopy(data, 32, parts[1], 0, 32); //right

        //Feistel
        for (int i = 0; i < 16; i++) {
            int[] leftOld = parts[0];
            int[] rightOld = parts[1];

            //F-Funktion
            int[] exp = Expansion.expand(rightOld);
            int[] xored = Bits.xor(exp, key.getNextKey());
            int[] sboxed = SBoxes.encrypt(xored);
            int[] perm = Permutation.permute(sboxed);

            int[] feddig = Bits.xor(perm, leftOld);

            //Next round
            parts[0] = rightOld;
            parts[1] = feddig;
        }

        int[] all = new int[64];
        System.arraycopy(parts[0], 0, all, 0, 32);
        System.arraycopy(parts[1], 0, all, 32, 32);

        int[] encrypted = Permutation.init_permute_inversed(all);
        System.out.println(Arrays.toString(encrypted));
    }
}
