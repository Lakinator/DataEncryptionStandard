import Algorithm.*;

import java.util.Arrays;


/**
 * 09.03.2018 | created by Lukas S
 */

public class Main {
    public static void main(String[] args) {

        int[] keyArr = new int[]{
                0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0
        };

        Key key = new Key(keyArr);

        for (int i = 0; i < 16; i++) {
            System.out.println(Arrays.toString(key.getNextKey()) + " (Round " + key.getCurrentRound() + ")");
        }

    }
}
