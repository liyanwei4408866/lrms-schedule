package com.pl.schedule.protocol.udp.model;

import java.util.Arrays;

import com.pl.schedule.protocol.udp.Utils;

public class ServerPara {
	private String serverIp;
	private long port;
	private int step;
	private byte[] data;

	public ServerPara(byte[] src) {
		this.data = Arrays.copyOf(src, src.length);
		this.decode();
	}

	private void decode() {
		this.serverIp = Utils.bytes2Ip(Arrays.copyOfRange(this.data, 4, 8));
		this.port = Utils.byteToInt(Arrays.copyOfRange(this.data, 8, 10));
		this.step = Integer.parseInt(Utils.toHexString(Arrays.copyOfRange(this.data, 10, 11)));
	}

	public String getServerIp() {
		return this.serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public long getPort() {
		return this.port;
	}

	public void setPort(int port) {
		this.port = (long) port;
	}

	public int getStep() {
		return this.step;
	}

	public void setStep(int step) {
		this.step = step;
	}
}