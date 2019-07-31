package com.pl.schedule.protocol.udp;

public class Cmd {
	public static byte[] SEARCH = new byte[]{25, -108, 0, 0};
	public static byte[] SETIP = new byte[]{25, -106, 0, 0};
	public static byte[] STATUS = new byte[]{25, 32, 0, 0};
	public static byte[] READTIME = new byte[]{25, 50, 0, 0};
	public static byte[] SETTIME = new byte[]{25, 48, 0, 0};
	public static byte[] GETRECBYIDX = new byte[]{25, -80, 0, 0};
	public static byte[] SETRECBYIDX = new byte[]{25, -78, 0, 0};
	public static byte[] GETRECIDX = new byte[]{25, -76, 0, 0};
	public static byte[] OPENDOOR = new byte[]{25, 64, 0, 0};
	public static byte[] MODAUTH = new byte[]{25, 80, 0, 0};
	public static byte[] DELAUTH = new byte[]{25, 82, 0, 0};
	public static byte[] CLEANAUTH = new byte[]{25, 84, 0, 0};
	public static byte[] COUNTAUTH = new byte[]{25, 88, 0, 0};
	public static byte[] QUERYAUTH = new byte[]{25, 90, 0, 0};
	public static byte[] SETPARA = new byte[]{25, -128, 0, 0};
	public static byte[] GETPARA = new byte[]{25, -126, 0, 0};
	public static byte[] SETSERVERIP = new byte[]{25, -112, 0, 0};
	public static byte[] GETSERVERIP = new byte[]{25, -110, 0, 0};

    public static void main(String[] args)
    {
        String aa = "051059078b894a3f20190619201906200100000000000000000000000000000000001410000000000000000000591405000000000000000000000000";

        System.out
                .println("startdate=" + aa.substring(16, 24) + "||" + aa.substring(92, 94) + ":" + aa.substring(94, 96)
                        + ":" + aa.substring(96, 98));
        System.out.println("enddate=" + aa.substring(24, 32) + "||" + aa.substring(68, 70) + ":" + aa.substring(70, 72)
                + ":" + aa.substring(90, 92));

    }
}