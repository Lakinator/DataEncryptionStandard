package Algorithm

import java.util.*

/**
 *  14.10.2018 | created by Lukas S
 */

class KeyGenerator {

    companion object {
        fun generate(): String {
            //TODO 8 sub rounds
            return Arrays.toString(sub())
        }

        private fun sub(): Array<Int> {
            val arr = Array(8) { _ -> (0..1).random() }
            //TODO last bit
            return arr
        }

        private fun IntRange.random() =
                Random().nextInt((endInclusive + 1) - start) + start
    }

}