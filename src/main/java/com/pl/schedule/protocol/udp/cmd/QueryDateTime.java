package com.pl.schedule.protocol.udp.cmd;

import java.util.Arrays;
import java.util.Date;

import com.pl.schedule.protocol.udp.Cmd;
import com.pl.schedule.protocol.udp.Utils;

public class QueryDateTime extends CommCmd {
	public Date getResult() {
		return Utils.toDateTime(Arrays.copyOfRange(this.recvBuff, 8, 15));
	}

	public QueryDateTime(String ip, byte[] sn) {
		super(ip, sn);
		this.copy(Cmd.READTIME, this.cmdBuf, 0, 0, 4);
		this.copy(sn, this.cmdBuf, 0, 4, 4);
	}
}