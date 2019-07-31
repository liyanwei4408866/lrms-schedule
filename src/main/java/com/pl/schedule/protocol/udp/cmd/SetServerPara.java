package com.pl.schedule.protocol.udp.cmd;

import java.util.Arrays;

import com.pl.schedule.protocol.udp.Cmd;
import com.pl.schedule.protocol.udp.Utils;

public class SetServerPara extends CommCmd {
	public long getResult() {
		return Utils.byteToInt(Arrays.copyOfRange(this.recvBuff, 8, 9));
	}

	public SetServerPara(String ip, byte[] sn, String serverIp, int serverPort, int step) {
		super(ip, sn);
		this.copy(Cmd.SETSERVERIP, this.cmdBuf, 0, 4);
		this.copy(sn, this.cmdBuf, 4, 4);
		this.copy(Utils.ip2Bytes(serverIp), this.cmdBuf, 8, 4);
		this.copy(Utils.int2Byte(serverPort, 2), this.cmdBuf, 12, 2);
		this.copy(Utils.int2Byte((long) step), this.cmdBuf, 14, 1);
	}
}