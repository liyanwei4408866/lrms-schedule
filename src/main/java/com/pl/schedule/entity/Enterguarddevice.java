package com.pl.schedule.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ENTERGUARDDEVICE")
public class Enterguarddevice implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    private String            id;
    private String            labid;
    private String            devicesn;
    private Integer           devicetype;
    private Integer           deviceprotocol;
    private Integer           commport;
    private String            ip;
    private Integer           port;
    private Integer           doorNo;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getLabid()
    {
        return labid;
    }

    public void setLabid(String labid)
    {
        this.labid = labid;
    }

    public String getDevicesn()
    {
        return devicesn;
    }

    public void setDevicesn(String devicesn)
    {
        this.devicesn = devicesn;
    }

    public Integer getDevicetype()
    {
        return devicetype;
    }

    public void setDevicetype(Integer devicetype)
    {
        this.devicetype = devicetype;
    }

    public Integer getDeviceprotocol()
    {
        return deviceprotocol;
    }

    public void setDeviceprotocol(Integer deviceprotocol)
    {
        this.deviceprotocol = deviceprotocol;
    }

    public Integer getCommport()
    {
        return commport;
    }

    public void setCommport(Integer commport)
    {
        this.commport = commport;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public Integer getPort()
    {
        return port;
    }

    public void setPort(Integer port)
    {
        this.port = port;
    }

    public Integer getDoorNo()
    {
        return doorNo;
    }

    public void setDoorNo(Integer doorNo)
    {
        this.doorNo = doorNo;
    }
}
