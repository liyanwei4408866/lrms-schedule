package com.pl.schedule.protocol.udp.cmd;import java.util.Arrays;import com.pl.schedule.protocol.udp.Cmd;import com.pl.schedule.protocol.udp.model.Auth;public class QuerytAuth extends CommCmd {	public Auth getResult() {		return new Auth(Arrays.copyOfRange(this.recvBuff, 4, this.recvBuff.length));	}	public QuerytAuth(String ip, byte[] sn, int cardNo) {		super(ip, sn);		this.copy(Cmd.COUNTAUTH, this.cmdBuf, 0, 4);		this.copy(sn, this.cmdBuf, 4, 4);	}}