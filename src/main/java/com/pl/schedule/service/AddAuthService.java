package com.pl.schedule.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pl.schedule.mapper.CommUserMapper;
import com.pl.schedule.protocol.udp.Utils;
import com.pl.schedule.protocol.udp.cmd.AddAuth;
import com.pl.schedule.protocol.udp.cmd.QueryAuth;
import com.pl.schedule.protocol.udp.model.Auth;
import com.pl.schedule.utils.LogUtil;
import com.pl.schedule.utils.UdpInfo;
import com.pl.schedule.vo.AuthInfo;

@Service
public class AddAuthService
{
    protected final Logger        LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    CommUserMapper         commUserMapper;

    public void addAuthByDay(Date data)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String nowStr = sdf.format(data);
        List<AuthInfo> list = commUserMapper.getAuthInfoByDate(nowStr);
        LOGGER.info("执行日期:{},待授权任务数:{}", nowStr, list.size());
        for (AuthInfo info : list)
        {
            LOGGER.info("待授权信息:{}", LogUtil.getObjectInfo(info));
            addAuth(info);
        }
    }

    // @Async
    public void addAuth(AuthInfo info)
    {
        AddAuth cmd = new AddAuth(info.getIp(), Utils.int2Byte(Long.parseLong(info.getDevicesn())), new Integer[]
        {
            1, 0, 0, 0
        }, Long.parseLong(info.getCardNo()), info.getStartTime(), info.getEndTime());
        try
        {
            cmd.execute();
        }
        catch (Exception e)
        {
            UdpInfo.sleep();
            addAuth(info);
            return;
        }
        if (1 != cmd.getResult())
        {
            UdpInfo.sleep();
            addAuth(info);
            return;
        }
        return;
    }

    public void queryAuth(AuthInfo info)
    {
        QueryAuth cmd = new QueryAuth(info.getIp(), Utils.int2Byte(Long.parseLong(info.getDevicesn())),
                Integer.parseInt(info.getCardNo()));
        cmd.execute();
        Auth auth = cmd.getResult();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(auth.getCardNo());
        System.out.println(sdf1.format(auth.getBeginDate()));
        System.out.println(sdf2.format(auth.getEndDate()));
        System.out.println(auth.getDoors()[0]);
        System.out.println(auth.getDoors()[1]);
        System.out.println(auth.getDoors()[2]);
        System.out.println(auth.getDoors()[3]);
    }

    public CommUserMapper getCommUserMapper()
    {
        return commUserMapper;
    }
}
