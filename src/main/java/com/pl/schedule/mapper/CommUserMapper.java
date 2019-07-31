package com.pl.schedule.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.pl.schedule.common.IBaseDao;
import com.pl.schedule.entity.CommUser;
import com.pl.schedule.vo.AuthInfo;

@Mapper
public interface CommUserMapper extends IBaseDao<CommUser>
{
	@Select("select a.startTime,a.endTime,b.cardNo,d.devicesn,d.doorNo,d.ip " 
           +"from lrms_instrumentreservation a,lrms_member b,lrms_lab c,enterguarddevice d,comm_user e " 
	       +"where a.member_id = b.id "
	       +"  and a.settlement_id = c.id "
	       +"  and c.ctrl_id = d.id "
	       +"  and b.user_id = e.id "
	       +"  and date_format(a.startTime,'%Y%m%d') = #{authDate, jdbcType=VARCHAR} "
	       +"  and a.status='1' "
	       +"  and a.isDelete='0' ")
    List<AuthInfo> getAuthInfoByDate(@Param("authDate") String authDate);
}
