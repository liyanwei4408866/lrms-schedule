package com.pl.schedule.protocol.udp.cmd;import java.util.Arrays;import com.pl.schedule.protocol.udp.Cmd;import com.pl.schedule.protocol.udp.Utils;import com.pl.schedule.protocol.udp.model.DoorSetting;public class GetDoorPara extends CommCmd {	public DoorSetting getResult() {		return new DoorSetting(Arrays.copyOfRange(this.recvBuff, 4, this.recvBuff.length));	}	public GetDoorPara(String ip, byte[] sn, int doorNo) {		super(ip, sn);		this.copy(Cmd.GETPARA, this.cmdBuf, 0, 4);		this.copy(sn, this.cmdBuf, 4, 4);		this.copy(Utils.int2Byte((long) doorNo), this.cmdBuf, 8, 1);	}}