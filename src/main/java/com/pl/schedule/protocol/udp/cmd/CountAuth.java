package com.pl.schedule.protocol.udp.cmd;

import java.util.Arrays;

import com.pl.schedule.protocol.udp.Cmd;
import com.pl.schedule.protocol.udp.Utils;

public class CountAuth extends CommCmd {
	public long getResult() {
		return Utils.byteToInt(Arrays.copyOfRange(this.recvBuff, 8, 12));
	}

	public CountAuth(String ip, byte[] sn) {
		super(ip, sn);
		this.copy(Cmd.COUNTAUTH, this.cmdBuf, 0, 4);
		this.copy(sn, this.cmdBuf, 4, 4);
	}
}