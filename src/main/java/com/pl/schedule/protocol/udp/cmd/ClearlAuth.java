package com.pl.schedule.protocol.udp.cmd;

import java.util.Arrays;

import com.pl.schedule.protocol.udp.Cmd;
import com.pl.schedule.protocol.udp.Utils;

public class ClearlAuth extends CommCmd {
	public long getResult() {
		return Utils.byteToInt(Arrays.copyOfRange(this.recvBuff, 8, 9));
	}

	public ClearlAuth(String ip, byte[] sn, int cardNo) {
		super(ip, sn);
		this.copy(Cmd.DELAUTH, this.cmdBuf, 0, 4);
		this.copy(sn, this.cmdBuf, 4, 4);
		this.copy(Utils.int2Byte((long) cardNo), this.cmdBuf, 8, 4);
	}
}