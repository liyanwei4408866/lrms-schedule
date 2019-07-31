package com.pl.schedule.protocol.udp.cmd;

import java.util.Arrays;

import com.pl.schedule.protocol.udp.Cmd;
import com.pl.schedule.protocol.udp.Const;
import com.pl.schedule.protocol.udp.Utils;

public class DelAuth extends CommCmd {
	public long getResult() {
		return this.recvBuff == null ? 0L : Utils.byteToInt(Arrays.copyOfRange(this.recvBuff, 8, 9));
	}

	public DelAuth(String ip, byte[] sn, int cardNo) {
		super(ip, sn);
		this.copy(Cmd.CLEANAUTH, this.cmdBuf, 0, 4);
		this.copy(sn, this.cmdBuf, 4, 4);
		this.copy(Const.FORCE_FLAG, this.cmdBuf, 8, 4);
	}
}