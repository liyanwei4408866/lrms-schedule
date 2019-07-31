package com.pl.schedule.protocol.udp.cmd;

import java.util.Arrays;

import com.pl.schedule.protocol.udp.Cmd;
import com.pl.schedule.protocol.udp.Utils;

public class OpenDoor extends CommCmd {
	public long getResult() {
		return Utils.byteToInt(Arrays.copyOfRange(this.recvBuff, 8, 9));
	}

	public OpenDoor(String ip, byte[] sn, int doorNo) {
		super(ip, sn);
		this.copy(Cmd.OPENDOOR, this.cmdBuf, 0, 4);
		this.copy(sn, this.cmdBuf, 4, 4);
		byte[] intBytes = Utils.int2Byte((long) doorNo);
		this.copy(intBytes, this.cmdBuf, 8, intBytes.length);
	}
}