package com.pl.schedule.utils;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UdpInfo
{
    @Value("${protocol.udp.serverIP}")
    private String        serverIP;
    @Value("${protocol.udp.serverPort}")
    private String        serverPort;
    @Value("${protocol.udp.reportPort}")
    private String        reportPort;

    private static String serverIP1;
    private static String serverPort1;
    private static String reportPort1;

    @PostConstruct
    public void setUdpInfo()
    {
        serverIP1 = this.serverIP;
        serverPort1 = this.serverPort;
        reportPort1 = this.reportPort;
    }

    public static String getServerIP()
    {
        return serverIP1;
    }

    public static int getServerPort()
    {
        return Integer.parseInt(serverPort1);
    }

    public static int getReportPort()
    {
        return Integer.parseInt(reportPort1);
    }

    public static void sleep()
    {
        try
        {
            Thread.sleep(1000L);
        }
        catch (InterruptedException var10)
        {
            var10.printStackTrace();
        }
    }
}
