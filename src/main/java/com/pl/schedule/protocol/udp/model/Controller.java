package com.pl.schedule.protocol.udp.model;

import java.util.Arrays;
import java.util.Date;

import com.pl.schedule.protocol.udp.Utils;

public class Controller
{
    private String sn;
    private String ip;
    private String mask;
    private String mac;
    private String gateway;
    private String drvVer;
    private Date   releaseDate;
	private byte[] data;

    public Controller(byte[] src)
    {
        this.data = src;
		this.decode();
	}

	private void decode() {
        this.sn = Utils.parseSn(Arrays.copyOfRange(this.data, 4, 8));
        this.ip = Utils.bytes2Ip(Arrays.copyOfRange(this.data, 8, 12));
        this.mask = Utils.bytes2Ip(Arrays.copyOfRange(this.data, 12, 16));
        this.gateway = Utils.bytes2Ip(Arrays.copyOfRange(this.data, 16, 20));
        this.mac = Utils.toHexString(Arrays.copyOfRange(this.data, 20, 26), "-");
        this.drvVer = Utils.toHexString(Arrays.copyOfRange(this.data, 26, 28), ".");
        this.releaseDate = Utils.toDate(Arrays.copyOfRange(this.data, 28, 32));
    }

    public byte[] getData()
    {
        return this.data;
    }

    public void setData(byte[] data)
    {
        this.data = data;
    }

    public byte[] getByteSn()
    {
        return Arrays.copyOfRange(this.data, 4, 8);
    }

    public byte[] getByteIp()
    {
        return Arrays.copyOfRange(this.data, 8, 12);
    }

    public String getSn()
    {
        this.sn = Utils.parseSn(this.getByteSn());
        return this.sn;
    }

    public void setSn(String sn)
    {
        this.sn = sn;
    }

    public String getIp()
    {
        return this.ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getMask()
    {
        return this.mask;
    }

    public void setMask(String mask)
    {
        this.mask = mask;
	}

    public String getMac()
    {
        return this.mac;
	}

    public void setMac(String mac)
    {
        this.mac = mac;
	}

    public String getGateway()
    {
        return this.gateway;
	}

    public void setGateway(String gateway)
    {
        this.gateway = gateway;
	}

    public String getDrvVer()
    {
        return this.drvVer;
	}

    public void setDrvVer(String drvVer)
    {
        this.drvVer = drvVer;
	}

    public Date getReleaseDate()
    {
        return this.releaseDate;
	}

    public void setReleaseDate(Date releaseDate)
    {
        this.releaseDate = releaseDate;
	}
}