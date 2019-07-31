package com.pl.schedule.protocol.udp.model;

import java.util.Arrays;

import com.pl.schedule.protocol.udp.Utils;

public class DoorSetting {
	private long doorNo;
	private long status;
	private long delay;
	private byte[] data;

	public DoorSetting(byte[] src) {
		this.data = Arrays.copyOf(src, src.length);
		this.decode();
	}

	private void decode() {
		this.doorNo = Utils.byteToInt(Arrays.copyOfRange(this.data, 4, 5));
		this.status = Utils.byteToInt(Arrays.copyOfRange(this.data, 5, 6));
		this.delay = Utils.byteToInt(Arrays.copyOfRange(this.data, 6, 7));
	}

	public long getDoorNo() {
		return this.doorNo;
	}

	public void setDoorNo(long doorNo) {
		this.doorNo = doorNo;
	}

	public long getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = (long) status;
	}

	public long getDelay() {
		return this.delay;
	}

	public void setDelay(int delay) {
		this.delay = (long) delay;
	}
}