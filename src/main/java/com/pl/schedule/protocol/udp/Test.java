package com.pl.schedule.protocol.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.pl.schedule.protocol.udp.cmd.AddAuth;
import com.pl.schedule.protocol.udp.cmd.CountAuth;
import com.pl.schedule.protocol.udp.cmd.DelAuth;
import com.pl.schedule.protocol.udp.cmd.GetRecord;
import com.pl.schedule.protocol.udp.cmd.GetRecordIndex;
import com.pl.schedule.protocol.udp.cmd.GetServerPara;
import com.pl.schedule.protocol.udp.cmd.OpenDoor;
import com.pl.schedule.protocol.udp.cmd.QueryAuth;
import com.pl.schedule.protocol.udp.cmd.QueryDateTime;
import com.pl.schedule.protocol.udp.cmd.QueryStatus;
import com.pl.schedule.protocol.udp.cmd.SearchCtrl;
import com.pl.schedule.protocol.udp.cmd.SetDateTime;
import com.pl.schedule.protocol.udp.cmd.SetIP;
import com.pl.schedule.protocol.udp.cmd.SetRecordIndex;
import com.pl.schedule.protocol.udp.cmd.SetServerPara;
import com.pl.schedule.protocol.udp.model.Auth;
import com.pl.schedule.protocol.udp.model.Controller;
import com.pl.schedule.protocol.udp.model.CtrlStatus;
import com.pl.schedule.protocol.udp.model.Record;
import com.pl.schedule.protocol.udp.model.ServerPara;

public class Test {
    // private static byte[] sn = new byte[]{-99, -89, 76, 13};
    private static byte[]           sn         = new byte[]
    {
        5, 16, 89, 7
    };
    private static byte[]           cardNo     = new byte[]
    {
        -117, -119, 74, 63
    };
    private static int              cardNum    = 1061849483;
    private static String           ip         = "10.1.20.123";
    private static String           localhostIp         = "10.1.20.169";
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	private static SimpleDateFormat DATEFORMAT = new SimpleDateFormat("yyyyMMdd");

	public static void main(String[] args) throws Exception {

        // for (byte a : Utils.int2Byte((long) 1061849483))
        // {
        // System.out.println(a);
        // }
        // System.out.println("--------c------");
        // for (byte a : NumberConvertByte.intToBytes(123277317))
        // {
        // System.out.println(a);
        // }
        // System.out.println("--------d------");
        // OpenDoor cmd = new OpenDoor(ip, sn, 1);
        // cmd.execute();
        // System.out.println(cmd.getResult());
        Test.testQueryAuth();
	}

	private static void testGetServerPara() {
        GetServerPara cmd = new GetServerPara(ip, sn);
		cmd.execute();
		ServerPara config = cmd.getResult();
		System.out.println(config.getServerIp());
		System.out.println(config.getPort());
		System.out.println(config.getStep());
	}

	private static void testSetServerPara() {
		SetServerPara cmd = new SetServerPara(ip, sn, "192.168.168.100", 8080, 10);
		cmd.execute();
		System.out.println(cmd.getResult());
	}

	private static void testCountAuth() {
		CountAuth cmd = new CountAuth(ip, sn);
		cmd.execute();
		System.out.println(cmd.getResult());
	}

	private static void testModAuth() throws ParseException {
		Integer[] doors = new Integer[]{1, 1, 1, 1};
		Date beginDate = DATEFORMAT.parse("20150101");
		Date endDate = DATEFORMAT.parse("20151231");
		AddAuth cmd = new AddAuth(ip, sn, doors, (long) cardNum, beginDate, endDate);
		cmd.execute();
		System.out.println(cmd.getResult());
	}

	private static void testDelAuth() {
		DelAuth cmd = new DelAuth(ip, sn, cardNum);
		cmd.execute();
		System.out.println(cmd.getResult());
	}

	private static void testQueryAuth() {
        QueryAuth cmd = new QueryAuth(localhostIp, sn, cardNum);
		cmd.execute();
		Auth auth = cmd.getResult();
		System.out.println(auth.getCardNo());
		System.out.println(auth.getBeginDate());
		System.out.println(auth.getEndDate());
		System.out.println(auth.getDoors()[0]);
		System.out.println(auth.getDoors()[1]);
		System.out.println(auth.getDoors()[2]);
		System.out.println(auth.getDoors()[3]);
	}

	private static void testAddAuth() throws ParseException {
		Integer[] doors = new Integer[]{1, 0, 0, 0};
		Date beginDate = DATEFORMAT.parse("20140101");
		Date endDate = DATEFORMAT.parse("20150101");
		AddAuth cmd = new AddAuth(ip, sn, doors, (long) cardNum, beginDate, endDate);
		cmd.execute();
		System.out.println(cmd.getResult());
	}

	private static void testOpenDoor() {
		OpenDoor cmd = new OpenDoor(ip, sn, 1);
		cmd.execute();
		System.out.println(cmd.getResult());
	}

	private static void testGetReadedIndex() {
		GetRecordIndex cmd = new GetRecordIndex(ip, sn);
		cmd.execute();
		System.out.println(cmd.getResult());
	}

	private static void testSetReadIndex() {
		SetRecordIndex cmd = new SetRecordIndex(ip, sn, 1);
		cmd.execute();
		System.out.println(cmd.getResult());
	}

	private static void testGetRecordByIndex() {
		GetRecord cmd = new GetRecord(ip, sn, 1);
		cmd.execute();
		Record rec = cmd.getResult();
		System.out.println(rec.getSn());
		System.out.println(rec.getIndex());
		System.out.println(rec.getType());
		System.out.println(rec.getResult());
		System.out.println(rec.getDoorNo());
		System.out.println(rec.getDirection());
		System.out.println(rec.getCardNo());
		System.out.println(rec.getErrorCode());
		System.out.println(rec.getTime());
	}

	private static void testSetDateTime() throws ParseException {
		SetDateTime cmd = new SetDateTime(ip, sn, sdf.parse("20140101080000"));
		cmd.execute();
		testQueryDateTime();
		SetDateTime cmd2 = new SetDateTime(ip, sn, new Date());
		cmd2.execute();
		testQueryDateTime();
	}

	private static void testQueryDateTime() {
		QueryDateTime cmd = new QueryDateTime(ip, sn);
		cmd.execute();
		System.out.println(cmd.getResult());
	}

	private static void testQueryStatus() {
		QueryStatus queryStatus = new QueryStatus("192.168.168.123", sn);
		queryStatus.execute();
		CtrlStatus status = queryStatus.getResult();
		System.out.println(status.getCardNum());
		System.out.println(status.getRecordIdx());
		System.out.println(status.getCardRecord());
		System.out.println(status.getCardResult());
		System.out.println(status.getDoor1Button());
		System.out.println(status.getDoor2Status());
		System.out.println(status.getDoor1Button());
		System.out.println(status.getDoor2Button());
		System.out.println(status.getCardDateTime());
		System.out.println(status.getCtrlDateTime());
	}

	private static void testSetIp() {
		SearchCtrl search = new SearchCtrl();
		search.execute();
		List<Controller> result = search.getResult();
		Iterator var3 = result.iterator();

		while (var3.hasNext()) {
			Controller ctrl = (Controller) var3.next();
			System.out.println(ctrl.getSn());
			System.out.println(ctrl.getMac());
			System.out.println(ctrl.getIp());
			System.out.println(ctrl.getMask());
			System.out.println(ctrl.getGateway());
			System.out.println(ctrl.getDrvVer());
			System.out.println(ctrl.getReleaseDate().toString());
		}

		System.out.println("");
		SetIP setIp = new SetIP("192.168.168.101", ((Controller) result.get(0)).getByteSn(), "255.255.255.0",
				"192.168.168.1");
		setIp.execute();
		search.execute();
		result = search.getResult();
		Iterator var4 = result.iterator();

		while (var4.hasNext()) {
			Controller ctrl = (Controller) var4.next();
			System.out.println(ctrl.getSn());
			System.out.println(ctrl.getMac());
			System.out.println(ctrl.getIp());
			System.out.println(ctrl.getMask());
			System.out.println(ctrl.getGateway());
			System.out.println(ctrl.getDrvVer());
			System.out.println(ctrl.getReleaseDate().toString());
		}

	}

	public static void findAllCtrl(List<String> ctrlSNs) throws IOException {
		DatagramSocket client = new DatagramSocket(60001);
		byte[] buf = new byte[64];
		copy(Cmd.SEARCH, buf, 0, 0, 4);
		InetAddress addr = InetAddress.getByName("255.255.255.0");
		DatagramPacket packet = new DatagramPacket(buf, buf.length, addr, 60000);
		client.send(packet);
		byte[] tmp = new byte[64];
		DatagramPacket p = new DatagramPacket(tmp, tmp.length);
		client.receive(p);
		byte[] result = p.getData();
	}

	public static void copy(byte[] ori, byte[] dest, int from, int offset, int length) {
		for (int i = 0; i < length; ++i) {
			dest[offset + i] = ori[from + i];
		}

	}
}