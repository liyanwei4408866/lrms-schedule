package com.pl.schedule.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pl.schedule.response.BaseResponse;
import com.pl.schedule.service.AccessControlRecordService;
import com.pl.schedule.service.AddAuthService;

@RestController
public class AddAuthController
{
    @Autowired
    AddAuthService             addAuthService;
    @Autowired
    AccessControlRecordService accessControlRecordService;

    @GetMapping(value = "/addAuth")
    public BaseResponse addAuth()
    {
        BaseResponse res = new BaseResponse();
        addAuthService.addAuthByDay(new Date());
        return res;
    }

    @GetMapping(value = "/addAuth/{date}")
    public BaseResponse addAuth(@PathVariable String date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        BaseResponse res = new BaseResponse();
        try
        {
            addAuthService.addAuthByDay(sdf.parse(date));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return res;
    }

    @GetMapping(value = "/downloadLog")
    public BaseResponse downloadLog()
    {
        BaseResponse res = new BaseResponse();
        accessControlRecordService.downloadLog();
        return res;
    }
}
