package com.pl.schedule.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService
{
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    AddAuthService         addAuthService;
    @Autowired
    AccessControlRecordService accessControlRecordService;

    // @Scheduled(cron = "0/30 * * * * ?")
    @Scheduled(cron = "0 0 3 * * ?")
    public void addAuthSchedule()
    {
        LOGGER.info("授权定时任务开始执行");
        addAuthService.addAuthByDay(new Date());
        LOGGER.info("授权定时任务执行完成");
    }

    // @Scheduled(cron = "10/30 * * * * ?")
    @Scheduled(cron = "0 0 1 * * ?")
    public void syncAccessLogSchedule()
    {
        LOGGER.info("下载刷卡记录定时任务开始执行");
        accessControlRecordService.downloadLog();
        LOGGER.info("下载刷卡记录定时任务执行完成");
    }
}
