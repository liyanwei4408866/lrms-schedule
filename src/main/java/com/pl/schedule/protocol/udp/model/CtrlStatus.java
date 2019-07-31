package com.pl.schedule.protocol.udp.model;

import java.util.Arrays;
import java.util.Date;

import com.pl.schedule.protocol.udp.Utils;

public class CtrlStatus {
	private byte[] data;
	private byte[] sn;
	private long recordIdx;
	private long cardRecord;
	private long cardResult;
	private long doorNo;
	private long doorDirection;
	private long cardNum;
	private Date cardDateTime;
	private long errCode;
	private long door1Status;
	private long door2Status;
	private long door3Status;
	private long door4Status;
	private long door1Button;
	private long door2Button;
	private long door3Button;
	private long door4Button;
	private long status;
	private Date ctrlDateTime;

	public CtrlStatus(byte[] src) {
		this.data = Arrays.copyOf(src, src.length);
		this.decode();
	}

	public void decode() {
		this.sn = Arrays.copyOfRange(this.data, 0, 4);
		this.recordIdx = Utils.byteToInt(Arrays.copyOfRange(this.data, 4, 8));
		this.cardRecord = Utils.byteToInt(Arrays.copyOfRange(this.data, 8, 9));
		this.cardResult = Utils.byteToInt(Arrays.copyOfRange(this.data, 9, 10));
		this.doorNo = Utils.byteToInt(Arrays.copyOfRange(this.data, 10, 11));
		this.doorDirection = Utils.byteToInt(Arrays.copyOfRange(this.data, 11, 12));
		this.cardNum = Utils.byteToInt(Arrays.copyOfRange(this.data, 12, 16));
		this.cardDateTime = Utils.toDateTime(Arrays.copyOfRange(this.data, 16, 23));
		this.errCode = Utils.byteToInt(Arrays.copyOfRange(this.data, 23, 24));
		this.door1Status = Utils.byteToInt(Arrays.copyOfRange(this.data, 24, 25));
		this.door2Status = Utils.byteToInt(Arrays.copyOfRange(this.data, 25, 26));
		this.door3Status = Utils.byteToInt(Arrays.copyOfRange(this.data, 26, 27));
		this.door4Status = Utils.byteToInt(Arrays.copyOfRange(this.data, 27, 28));
		this.door1Button = Utils.byteToInt(Arrays.copyOfRange(this.data, 28, 29));
		this.door2Button = Utils.byteToInt(Arrays.copyOfRange(this.data, 29, 30));
		this.door3Button = Utils.byteToInt(Arrays.copyOfRange(this.data, 30, 31));
		this.door4Button = Utils.byteToInt(Arrays.copyOfRange(this.data, 31, 32));
		this.ctrlDateTime = Utils.toTime(Arrays.copyOfRange(this.data, 32, 35));
	}

	public byte[] getSn() {
		return this.sn;
	}

	public void setSn(byte[] sn) {
		this.sn = sn;
	}

	public long getRecordIdx() {
		return this.recordIdx;
	}

	public void setRecordIdx(long recordIdx) {
		this.recordIdx = recordIdx;
	}

	public long getCardRecord() {
		return this.cardRecord;
	}

	public void setCardRecord(int cardRecord) {
		this.cardRecord = (long) cardRecord;
	}

	public long getCardResult() {
		return this.cardResult;
	}

	public void setCardResult(int cardResult) {
		this.cardResult = (long) cardResult;
	}

	public long getDoorNo() {
		return this.doorNo;
	}

	public void setDoorNo(int doorNo) {
		this.doorNo = (long) doorNo;
	}

	public long getDoorDirection() {
		return this.doorDirection;
	}

	public void setDoorDirection(int doorDirection) {
		this.doorDirection = (long) doorDirection;
	}

	public long getCardNum() {
		return this.cardNum;
	}

	public void setCardNum(int cardNum) {
		this.cardNum = (long) cardNum;
	}

	public Date getCardDateTime() {
		return this.cardDateTime;
	}

	public void setCardDateTime(Date cardDateTime) {
		this.cardDateTime = cardDateTime;
	}

	public long getErrCode() {
		return this.errCode;
	}

	public void setErrCode(long errCode) {
		this.errCode = errCode;
	}

	public long getDoor1Status() {
		return this.door1Status;
	}

	public void setDoor1Status(long door1Status) {
		this.door1Status = door1Status;
	}

	public long getDoor2Status() {
		return this.door2Status;
	}

	public void setDoor2Status(long door2Status) {
		this.door2Status = door2Status;
	}

	public long getDoor3Status() {
		return this.door3Status;
	}

	public void setDoor3Status(long door3Status) {
		this.door3Status = door3Status;
	}

	public long getDoor4Status() {
		return this.door4Status;
	}

	public void setDoor4Status(long door4Status) {
		this.door4Status = door4Status;
	}

	public long getDoor1Button() {
		return this.door1Button;
	}

	public void setDoor1Button(long door1Button) {
		this.door1Button = door1Button;
	}

	public long getDoor2Button() {
		return this.door2Button;
	}

	public void setDoor2Button(int door2Button) {
		this.door2Button = (long) door2Button;
	}

	public long getDoor3Button() {
		return this.door3Button;
	}

	public void setDoor3Button(long door3Button) {
		this.door3Button = door3Button;
	}

	public long getDoor4Button() {
		return this.door4Button;
	}

	public void setDoor4Button(int door4Button) {
		this.door4Button = (long) door4Button;
	}

	public long getStatus() {
		return this.status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public Date getCtrlDateTime() {
		return this.ctrlDateTime;
	}

	public void setCtrlDateTime(Date ctrlDateTime) {
		this.ctrlDateTime = ctrlDateTime;
	}
}