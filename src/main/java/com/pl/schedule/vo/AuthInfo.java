package com.pl.schedule.vo;

import java.util.Date;

public class AuthInfo
{
    private Date   startTime;
    private Date   endTime;
    private String cardNo;
    private String devicesn;
    private String doorNo;
    private String ip;

    public Date getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public String getCardNo()
    {
        return cardNo;
    }

    public void setCardNo(String cardNo)
    {
        this.cardNo = cardNo;
    }

    public String getDevicesn()
    {
        return devicesn;
    }

    public void setDevicesn(String devicesn)
    {
        this.devicesn = devicesn;
    }

    public String getDoorNo()
    {
        return doorNo;
    }

    public void setDoorNo(String doorNo)
    {
        this.doorNo = doorNo;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }
}
