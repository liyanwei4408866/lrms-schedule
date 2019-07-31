package com.pl.schedule.protocol.udp.cmd;

import java.util.Arrays;
import java.util.Date;

import com.pl.schedule.protocol.udp.Cmd;
import com.pl.schedule.protocol.udp.Utils;

public class AddAuth extends CommCmd {
	public long getResult() {
		return Utils.byteToInt(Arrays.copyOfRange(this.recvBuff, 8, 9));
	}

	public AddAuth(String ip, byte[] sn, Integer[] doors, long cardNo, Date beginDate, Date endDate) {
		super(ip, sn);
		this.copy(Cmd.MODAUTH, this.cmdBuf, 0, 4);
		this.copy(sn, this.cmdBuf, 4, 4);
		this.copy(Utils.int2Byte(cardNo), this.cmdBuf, 8, 4);
		this.copy(Utils.date2Bytes(beginDate), this.cmdBuf, 12, 4);
		this.copy(Utils.date2Bytes(endDate), this.cmdBuf, 16, 4);
		byte[] tmp = new byte[1];

		for (int i = 0; i < 4; ++i) {
			int isOpen = doors[i];
			if (isOpen == 1) {
				tmp[0] = 1;
				this.copy(tmp, this.cmdBuf, 20 + i, 1);
			} else {
				tmp[0] = 0;
				this.copy(tmp, this.cmdBuf, 20 + i, 1);
			}
		}
        // 24-26 用户密码
        for (int i = 0; i < 3; ++i)
        {
            tmp[0] = 0;
            this.copy(tmp, this.cmdBuf, 24 + i, 1);
        }
        // 27-37 11个占位
        for (int i = 0; i < 11; ++i)
        {
            tmp[0] = 0;
            this.copy(tmp, this.cmdBuf, 27 + i, 1);
        }
        // 截止小时
        this.copy(Utils.hourToBytes(endDate), this.cmdBuf, 38, 1);
        // 截止分钟
        this.copy(Utils.minuteToBytes(endDate), this.cmdBuf, 39, 1);

        // 40-48 9个占位
        for (int i = 0; i < 11; ++i)
        {
            tmp[0] = 0;
            this.copy(tmp, this.cmdBuf, 40 + i, 1);
        }
        // 截止秒
        this.copy(Utils.secondToBytes(endDate), this.cmdBuf, 49, 1);
        // 开始小时
        this.copy(Utils.hourToBytes(beginDate), this.cmdBuf, 50, 1);
        // 开始分钟
        this.copy(Utils.minuteToBytes(beginDate), this.cmdBuf, 51, 1);
        // 开始秒
        this.copy(Utils.secondToBytes(beginDate), this.cmdBuf, 52, 1);

	}

    // public AddAuth(String ip, byte[] sn, Integer[] doors, long cardNo, Date beginDate, Date endDate, boolean flag)
    // {
    // super(ip, sn);
    // // 功能编码
    // this.copy(Cmd.MODAUTH, this.cmdBuf, 0, 4);
    // this.copy(sn, this.cmdBuf, 4, 4);
    // this.copy(Utils.int2Byte(cardNo), this.cmdBuf, 8, 4);
    // this.copy(Utils.date2Bytes(beginDate), this.cmdBuf, 12, 4);
    // this.copy(Utils.date2Bytes(endDate), this.cmdBuf, 16, 4);
    // byte[] tmp = new byte[1];
    //
    // for (int i = 0; i < 4; ++i)
    // {
    // int isOpen = doors[i];
    // if (isOpen == 1)
    // {
    // tmp[0] = 1;
    // this.copy(tmp, this.cmdBuf, 20 + i, 1);
    // }
    // else
    // {
    // tmp[0] = 0;
    // this.copy(tmp, this.cmdBuf, 20 + i, 1);
    // }
    // }
    // // 24-26 用户密码
    // for (int i = 0; i < 3; ++i)
    // {
    // tmp[0] = 0;
    // this.copy(tmp, this.cmdBuf, 24 + i, 1);
    // }
    // // 27-37 11个占位
    // for (int i = 0; i < 11; ++i)
    // {
    // tmp[0] = 0;
    // this.copy(tmp, this.cmdBuf, 27 + i, 1);
    // }
    // // 截止小时
    // this.copy(Utils.hourToBytes(beginDate), this.cmdBuf, 38, 1);
    // // 截止分钟
    // this.copy(Utils.minuteToBytes(endDate), this.cmdBuf, 39, 1);
    //
    // // 40-48 9个占位
    // for (int i = 0; i < 11; ++i)
    // {
    // tmp[0] = 0;
    // this.copy(tmp, this.cmdBuf, 40 + i, 1);
    // }
    // // 截止秒
    // this.copy(Utils.secondToBytes(endDate), this.cmdBuf, 49, 1);
    // // 开始小时
    // this.copy(Utils.hourToBytes(endDate), this.cmdBuf, 50, 1);
    // // 开始分钟
    // this.copy(Utils.minuteToBytes(endDate), this.cmdBuf, 51, 1);
    // // 开始秒
    // this.copy(Utils.secondToBytes(endDate), this.cmdBuf, 52, 1);
    //
    // }
}