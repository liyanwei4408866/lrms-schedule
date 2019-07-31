package com.pl.schedule.protocol.udp;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class NumberConvertByte
{
    private static final int BYTE_BIT_LENGTH   = 8;
    private static final int INT_BYTE_LENGTH   = 4;
    private static final int SHORT_BYTE_LENGTH = 2;
    private static final int BYTE_BYTE_LENGTH  = 1;

    public static Byte[] intToBytes(int number)
    {
        Byte[] bytes = new Byte[INT_BYTE_LENGTH];
        for (int i = 0; i < INT_BYTE_LENGTH; i++)
        {
            bytes[i] = (byte) (number >> (BYTE_BIT_LENGTH * i));
        }
        return bytes;
    }

    public static Byte[] shortToBytes(short number)
    {
        Byte[] bytes = new Byte[SHORT_BYTE_LENGTH];
        for (int i = 0; i < SHORT_BYTE_LENGTH; i++)
        {
            bytes[i] = (byte) (number >> (BYTE_BIT_LENGTH * i));
        }
        return bytes;
    }

    public static Byte[] byteToBytes(Byte number)
    {
        return new Byte[]
        {
            number
        };
    }

    public static int bytesToInt(byte[] sn)
    {
        byte[] bytes1 = new byte[sn.length];
        for (int i = 0; i < bytes1.length; i++)
        {
            bytes1[i] = sn[i];

        }
        return new BigInteger(bytes1).intValue();
    }

    // 合并数组
    public static Byte[] mergeBytes(Byte[]... bytes)
    {
        List<Byte> list = new ArrayList();
        for (Byte[] aByte : bytes)
        {
            for (byte b : aByte)
            {
                list.add(b);
            }
        }
        Byte[] reBytes = new Byte[list.size()];
        list.toArray(reBytes);
        return reBytes;
    }

    // 拆分数组
    public static Byte[] subBytes(Byte[] bytes, int start, int end)
    {
        Byte[] byteArray = new Byte[end - start];
        for (int i = start; i < end; i++)
        {
            byteArray[i - start] = bytes[i];
        }
        return byteArray;
    }
}
