package com.pl.schedule.protocol.udp.cmd;import java.util.Arrays;import com.pl.schedule.protocol.udp.Cmd;import com.pl.schedule.protocol.udp.Utils;import com.pl.schedule.protocol.udp.model.Record;public class GetRecord extends CommCmd {	public Record getResult() {		return new Record(Arrays.copyOfRange(this.recvBuff, 4, this.recvBuff.length));	}	public GetRecord(String ip, byte[] sn, int index) {		super(ip, sn);		this.copy(Cmd.GETRECBYIDX, this.cmdBuf, 0, 4);		this.copy(sn, this.cmdBuf, 4, 4);		byte[] t = Utils.int2Byte((long) index);		this.copy(t, this.cmdBuf, 8, 4);	}}