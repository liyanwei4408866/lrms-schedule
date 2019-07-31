package com.pl.schedule.protocol.udp.model;

import java.util.Arrays;
import java.util.Date;

import com.pl.schedule.protocol.udp.Utils;

public class Record {
	private byte[] data;
	private String sn;
	private long index;
	private long type;
	private long result;
	private long doorNo;
	private long direction;
	private long cardNo;
	private Date time;
	private long errorCode;

	public Record(byte[] src) {
		this.data = Arrays.copyOf(src, src.length);
		this.decode();
	}

	private void decode() {
		this.sn = Utils.parseSn(Arrays.copyOfRange(this.data, 0, 4));
		this.index = Utils.byteToInt(Arrays.copyOfRange(this.data, 4, 8));
		this.type = Utils.byteToInt(Arrays.copyOfRange(this.data, 8, 9));
		this.result = Utils.byteToInt(Arrays.copyOfRange(this.data, 9, 10));
		this.doorNo = Utils.byteToInt(Arrays.copyOfRange(this.data, 10, 11));
		this.direction = Utils.byteToInt(Arrays.copyOfRange(this.data, 11, 12));
		this.cardNo = Utils.byteToInt(Arrays.copyOfRange(this.data, 12, 16));
		this.time = Utils.toDateTime(Arrays.copyOfRange(this.data, 16, 23));
		this.errorCode = Utils.byteToInt(Arrays.copyOfRange(this.data, 23, 24));
	}

	public String getSn() {
		return this.sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public long getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = (long) index;
	}

	public long getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = (long) type;
	}

	public long getResult() {
		return this.result;
	}

	public void setResult(int result) {
		this.result = (long) result;
	}

	public long getDoorNo() {
		return this.doorNo;
	}

	public void setDoorNo(int doorNo) {
		this.doorNo = (long) doorNo;
	}

	public long getDirection() {
		return this.direction;
	}

	public void setDirection(int direction) {
		this.direction = (long) direction;
	}

	public long getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(int cardNo) {
		this.cardNo = (long) cardNo;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public long getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = (long) errorCode;
	}
}