package com.pl.schedule.protocol.udp.cmd;

import com.pl.schedule.protocol.udp.Cmd;
import com.pl.schedule.protocol.udp.Const;
import com.pl.schedule.protocol.udp.Utils;

public class SetIP extends CommCmd {
	public SetIP(String ip, byte[] sn, String mask, String gateway) {
		super("255.255.255.255", sn);
		this.copy(Cmd.SETIP, this.cmdBuf, 0, 0, 4);
		this.copy(sn, this.cmdBuf, 0, 4, 4);
		byte[] ipBytes = Utils.ip2Bytes(ip);
		this.copy(ipBytes, this.cmdBuf, 0, 8, 4);
		byte[] maskBytes = Utils.ip2Bytes(mask);
		this.copy(maskBytes, this.cmdBuf, 0, 12, 4);
		byte[] gatewayBytes = Utils.ip2Bytes(gateway);
		this.copy(gatewayBytes, this.cmdBuf, 0, 16, 4);
		this.copy(Const.FORCE_FLAG, this.cmdBuf, 0, 20, 4);
		this.rightPad(this.cmdBuf, (byte) 0, 24, this.cmdBuf.length - 24);
	}

	public void recv() {
	}
}