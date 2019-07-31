package com.pl.schedule.protocol.udp.cmd;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import com.pl.schedule.protocol.udp.Cmd;
import com.pl.schedule.protocol.udp.model.Controller;

public class SearchCtrl extends CommCmd {
	private static byte[] nullSn = new byte[4];
	private List<Controller> controllers = new ArrayList();

	public List<Controller> getResult() {
		return this.controllers;
	}

	public SearchCtrl() {
		super("255.255.255.255", nullSn);
		this.copy(Cmd.SEARCH, this.cmdBuf, 0, 0, Cmd.SEARCH.length);
		this.rightPad(this.cmdBuf, (byte) 0, 4, this.cmdBuf.length);
	}

	public void recv() {
		byte[] tmpBuff = new byte[64];

		try {
			conn.setSoTimeout(3000);
		} catch (SocketException var5) {
			var5.printStackTrace();
		}

		while (true) {
			DatagramPacket p = new DatagramPacket(tmpBuff, 64);

			try {
				conn.receive(p);
				Controller ctrl = new Controller(p.getData());
				this.controllers.add(ctrl);
			} catch (IOException var4) {
				var4.printStackTrace();
				return;
			}
		}
	}
}

class RecvThread extends Thread
{
    private DatagramSocket soc;
    private boolean        isRunning = true;
    private List<byte[]>   recvDatas = new ArrayList();

    public RecvThread(DatagramSocket conn)
    {
        this.soc = conn;

        try {
            this.soc.setSoTimeout(3000);
        } catch (SocketException var3) {
            var3.printStackTrace();
        }

    }

    public boolean isRunning()
    {
        return this.isRunning;
    }

    public void setRunning(boolean isRunning)
    {
        this.isRunning = isRunning;
    }

    public List<byte[]> getRecvDatas()
    {
        return this.recvDatas;
    }

    public void setRecvDatas(List<byte[]> recvDatas)
    {
        this.recvDatas = recvDatas;
    }

    public void run()
    {
        while (this.isRunning)
        {
            byte[] tmpBuf = new byte[64];
            DatagramPacket recvPack = new DatagramPacket(tmpBuf, tmpBuf.length);

            try
            {
                this.soc.receive(recvPack);
                this.recvDatas.add(recvPack.getData());
            }
            catch (IOException var4)
            {
                var4.printStackTrace();
            }
        }

    }
}