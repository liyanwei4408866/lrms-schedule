package com.pl.schedule.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pl.schedule.entity.Enterguarddevice;
import com.pl.schedule.entity.LrmsAccessControlRecord;
import com.pl.schedule.mapper.EnterguarddeviceMapper;
import com.pl.schedule.mapper.LrmsAccessControlRecordMapper;
import com.pl.schedule.protocol.udp.Utils;
import com.pl.schedule.protocol.udp.cmd.GetRecord;
import com.pl.schedule.protocol.udp.model.Record;
import com.pl.schedule.utils.IDGen;
import com.pl.schedule.utils.LogUtil;
import com.pl.schedule.utils.UdpInfo;

@Service
public class AccessControlRecordService
{
    protected final Logger        LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    LrmsAccessControlRecordMapper lrmsAccessControlRecordMapper;
    @Autowired
    EnterguarddeviceMapper        enterguarddeviceMapper;

    private final int             maxTryCount = 2;

    public void downloadLog()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String batchId = sdf.format(new Date()) + "-" + IDGen.shortId();
        LOGGER.info("开始下载刷卡日志,批次号:{}", batchId);
        List<Enterguarddevice> list = enterguarddeviceMapper.getAll();
        LOGGER.debug("等待执行设备数:{}", list.size());
        boolean isAdd = false;
        if (list != null && list.size() > 0)
        {
            for (Enterguarddevice info : list)
            {
                LOGGER.info("设备信息,ip:{},sn:{}", info.getIp(), info.getDevicesn());
                // 获取本地上次最后一条记录的index
                long lastLocalIndex = lrmsAccessControlRecordMapper.getMaxIndexBySn(info.getDevicesn());
                LOGGER.debug("本地上次最后一条记录的index:{}", lastLocalIndex);
                // 获取设备上最新记录的index
                long lastDeviceIndex = getEndIndex(info.getIp(), info.getDevicesn(), 1);
                LOGGER.debug("设备上最新记录的index:{}", lastDeviceIndex);
                for (long i = lastLocalIndex + 1; i <= lastDeviceIndex; i++)
                {
                    LrmsAccessControlRecord entity = getAccessControlRecord(info.getIp(), info.getDevicesn(), (int) i,
                            1);
                    LOGGER.debug("LrmsAccessControlRecord:{}", LogUtil.getObjectInfo(entity));
                    entity.setBatchId(batchId);
                    lrmsAccessControlRecordMapper.insert(entity);
                    if (!isAdd)
                    {
                        isAdd = true;
                    }
                }
            }
            if (isAdd)
            {
                lrmsAccessControlRecordMapper.insertAccesslog(batchId);
            }
        }
    }

    public long getEndIndex(String ip, String sn, int num)
    {
        if (num >= maxTryCount)
        {
            return 0l;
        }
        num++;
        GetRecord cmd = new GetRecord(ip, Utils.int2Byte(Long.parseLong(sn)), -1);
        Record rec = null;
        try
        {
            LOGGER.debug("cmd.execute();");
            cmd.execute();
            UdpInfo.sleep();
            LOGGER.debug("rec = cmd.getResult();");
            rec = cmd.getResult();
        }
        catch (Throwable var2)
        {
            UdpInfo.sleep();
            LOGGER.debug("catch:{}", var2);
            return getEndIndex(ip, sn, num);
        }
        if (rec == null)
        {
            LOGGER.debug("rec == null");
            return 0l;
        }
        if (!rec.getSn().equals(sn))
        {
            LOGGER.debug("!rec.getSn().equals(sn)");
            UdpInfo.sleep();
            return getEndIndex(ip, sn, num);
        }
        return rec.getIndex();
    }

    public LrmsAccessControlRecord getAccessControlRecord(String ip, String sn, Integer index, int num)
    {
        if (num >= maxTryCount)
        {
            return null;
        }
        num++;
        GetRecord cmd = new GetRecord(ip, Utils.int2Byte(Long.parseLong(sn)), index);
        Record rec = null;
        try
        {
            cmd.execute();
            UdpInfo.sleep();
            rec = cmd.getResult();
        }
        catch (Throwable var2)
        {
            UdpInfo.sleep();
            return getAccessControlRecord(ip, sn, index, num);
        }
        if (rec == null)
        {
            return null;
        }
        if (!rec.getSn().equals(sn))
        {
            UdpInfo.sleep();
            return getAccessControlRecord(ip, sn, index, num);
        }
        LrmsAccessControlRecord entity = new LrmsAccessControlRecord();
        entity.setId(IDGen.uuid());
        entity.setSn(sn);
        entity.setRecordIndex(rec.getIndex());
        entity.setRecordType((int) rec.getType());
        entity.setRecordResult((int) rec.getResult());
        entity.setDoorno((int) rec.getDoorNo());
        entity.setDirection((int) rec.getDirection());
        entity.setCardno(Long.toString(rec.getCardNo()));
        entity.setRecordAccesstime(rec.getTime());
        entity.setRecordErrorcode((int) rec.getErrorCode());
        return entity;
    }
}
