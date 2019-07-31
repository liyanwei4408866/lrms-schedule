package com.pl.schedule.protocol.udp.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import com.pl.schedule.protocol.udp.Utils;

public class Auth {
	private Date beginDate;
	private Date endDate;
	private Long[] doors = new Long[4];
	private long cardNo;
	private byte[] data;

	public Auth(byte[] src) {
		this.data = Arrays.copyOf(src, src.length);
		this.decode();
	}

	private void decode() {
		this.cardNo = Utils.byteToInt(Arrays.copyOfRange(this.data, 4, 8));
		String dataStr = Utils.toHexString(this.data);
		String beginDateStr = dataStr.substring(16, 24) + dataStr.substring(92, 94) + dataStr.substring(94, 96) + dataStr.substring(96, 98);
        String endDateStr = dataStr.substring(24, 32) + dataStr.substring(68, 70) + dataStr.substring(70, 72)
                + dataStr.substring(90, 92);
        // this.beginDate = Utils.toDate(Arrays.copyOfRange(this.data, 8, 12));
        // this.endDate = Utils.toDate(Arrays.copyOfRange(this.data, 12, 16));

		for (int i = 0; i < 4; ++i) {
			long isOpen = Utils.byteToInt(Arrays.copyOfRange(this.data, 16 + i, 16 + i + 1));
			this.doors[i] = isOpen;
		}
        try
        {
            this.beginDate = new SimpleDateFormat("yyyyMMddHHmmss").parse(beginDateStr);
            this.endDate = new SimpleDateFormat("yyyyMMddHHmmss").parse(endDateStr);

        }
        catch (ParseException var3)
        {
            var3.printStackTrace();
            this.beginDate = new Date(0L);
            this.endDate = new Date(0L);
        }
	}

	public Date getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long[] getDoors() {
		return this.doors;
	}

	public void setDoors(Long[] doors) {
		this.doors = doors;
	}

	public long getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(int cardNo) {
		this.cardNo = (long) cardNo;
	}

    public byte[] getData()
    {
        return data;
    }
}