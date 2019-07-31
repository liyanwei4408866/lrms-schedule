package com.pl.schedule.protocol.udp.cmd;

import java.util.Arrays;

import com.pl.schedule.protocol.udp.Cmd;
import com.pl.schedule.protocol.udp.Const;
import com.pl.schedule.protocol.udp.Utils;

public class SetRecordIndex extends CommCmd {
	public long getResult() {
		return Utils.byteToInt(Arrays.copyOfRange(this.recvBuff, 8, 9));
	}

	public SetRecordIndex(String ip, byte[] sn, int index) {
		super(ip, sn);
		this.copy(Cmd.SETRECBYIDX, this.cmdBuf, 0, 0, 4);
		this.copy(sn, this.cmdBuf, 0, 4, 4);
		byte[] intBytes = Utils.int2Byte((long) index);
		this.copy(intBytes, this.cmdBuf, 0, 8, 4);
		this.copy(Const.FORCE_FLAG, this.cmdBuf, 0, 12, 4);
	}
}