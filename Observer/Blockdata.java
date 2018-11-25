package Observer;

import Algorithm.Bits;
import Algorithm.Key;
import Algorithm.Message;

/**
 * 11.11.2018 | created by Lukas S
 */

public class Blockdata {
    private int blockIndex;
    private Message msgStart;
    private Key key;
    private int[] IP, IP_Inv;
    private Rounddata[] rounddata;

    public Blockdata(int blockIndex, Message msgStart) {
        this.blockIndex = blockIndex;
        this.msgStart = msgStart;
        this.rounddata = new Rounddata[16];
    }

    public void setMsgStart(Message msgStart) {
        this.msgStart = msgStart;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public void setIP(int[] IP) {
        this.IP = IP;
    }

    public void setIP_Inv(int[] IP_Inv) {
        this.IP_Inv = IP_Inv;
    }

    public void setRounddata(Rounddata rounddata) {
        this.rounddata[rounddata.getRound()-1] = rounddata;
    }

    public int getBlockIndex() {
        return blockIndex;
    }

    public Message getMsgStart() {
        return msgStart;
    }

    public Key getKey() {
        return key;
    }

    public int[] getIP() {
        return IP;
    }

    public int[] getIP_Inv() {
        return IP_Inv;
    }

    public Rounddata[] getRounddata() {
        return rounddata;
    }

    private String rounddataAsString(String spaces) {
        StringBuilder ret = new StringBuilder("rounddata");

        for (int i = 0; i < rounddata.length; i++) {
            ret.append("\n  ").append(spaces).append(i).append("\n").append(rounddata[i].toString(spaces + "  "));
        }

        return ret.toString();
    }

    public String toString(String spaces) {
        return String.format("%s  blockindex  = %s\n" +
                             "%s  msgStart\n%s\n" +
                             "%s  %s\n" +
                             "%s  IP          = %s\n" +
                             "%s  IP_Inv      = %s\n" +
                             "%s  %s",
                spaces, blockIndex, spaces, msgStart.toString(spaces + "  "), spaces, key.toString(spaces + "  "), spaces, Bits.toString(IP), spaces, Bits.toString(IP_Inv), spaces, rounddataAsString(spaces + "  "));
    }
}
