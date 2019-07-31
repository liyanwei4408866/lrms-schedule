package com.pl.schedule.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.pl.schedule.common.IBaseDao;
import com.pl.schedule.entity.Enterguarddevice;

@Mapper
public interface EnterguarddeviceMapper extends IBaseDao<Enterguarddevice>
{
    @Select("select b.labid,b.devicesn,b.ip,b.port,b.doorNo " 
           +"from lrms_lab a,enterguarddevice b " 
           +"where a.isDelete='0' "
           +"  and a.ctrl_id = b.id ")
    List<Enterguarddevice> getAll();
}
