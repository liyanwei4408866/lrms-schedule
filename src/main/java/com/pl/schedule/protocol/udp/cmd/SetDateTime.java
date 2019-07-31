package com.pl.schedule.protocol.udp.cmd;

import java.util.Date;

import com.pl.schedule.protocol.udp.Cmd;
import com.pl.schedule.protocol.udp.Utils;

public class SetDateTime extends CommCmd {
	public SetDateTime(String ip, byte[] sn, Date dateTime) {
		super(ip, sn);
		this.copy(Cmd.SETTIME, this.cmdBuf, 0, 0, 4);
		this.copy(sn, this.cmdBuf, 0, 4, 4);
		byte[] dtBytes = Utils.dateTime2Bytes(dateTime);
		this.copy(dtBytes, this.cmdBuf, 0, 8, 7);
	}
}