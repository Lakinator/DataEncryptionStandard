package Observer;

import Algorithm.DES;
import Algorithm.Message;

/**
 * 11.11.2018 | created by Lukas S
 */

public class Observer {
    private Message msgStart, msgEnd;
    private DES.BLOCK_MODE block_mode;
    private Blockdata[] blockdata;

    public Observer(Message msgStart) {
        this.msgStart = msgStart;
        this.blockdata = new Blockdata[msgStart.getBlockCount()];
    }

    public Message getMsgEnd() {
        return msgEnd;
    }

    public void setMsgEnd(Message msgEnd) {
        this.msgEnd = msgEnd;
    }

    public Blockdata[] getBlockdata() {
        return blockdata;
    }

    public void setBlockdata(Blockdata blockdata) {
        this.blockdata[blockdata.getBlockIndex()] = blockdata;
    }

    public DES.BLOCK_MODE getBlock_mode() {
        return block_mode;
    }

    public void setBlock_mode(DES.BLOCK_MODE block_mode) {
        this.block_mode = block_mode;
    }
}
