package com.pl.schedule.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.pl.schedule.common.IBaseDao;
import com.pl.schedule.entity.LrmsAccessControlRecord;

@Mapper
public interface LrmsAccessControlRecordMapper extends IBaseDao<LrmsAccessControlRecord>
{
    @Select("select ifnull(max(record_index),0) recordindex " 
            +"from lrms_access_control_record " 
            +"where sn = #{sn, jdbcType=VARCHAR}")
     Long getMaxIndexBySn(@Param("sn")String sn);
    
    @Insert("insert into accesslog(id,memberid,labid,entryTime,outtime) "
           +"select a.id,d.id,c.id,record_accesstime,record_accesstime "
           +"from lrms_access_control_record a,enterguarddevice b,lrms_lab c,lrms_member d "
           +"where a.batch_id = #{batchId, jdbcType=VARCHAR} and a.record_type = 1 "
           +"  and a.record_result = 1 "
           +"  and d.isDelete = 0 "
           +"  and a.cardno = d.cardNo "
           +"  and a.sn = b.devicesn "
           +"  and b.id = c.ctrl_id "
           +"  and not EXISTS ( "
           +"    select 1 from accesslog al "
           +"    where al.memberid=d.id "
           +"      and al.labid = c.id "
           +"      and al.entryTime = a.record_accesstime) ")
    void insertAccesslog(@Param("batchId")String batchId);
}
