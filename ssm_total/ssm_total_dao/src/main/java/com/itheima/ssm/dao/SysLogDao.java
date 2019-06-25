package com.itheima.ssm.dao;

import com.itheima.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysLogDao {
    @Insert("insert into syslog (visitTime,username,ip,url,executionTime,method) values (#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void save(SysLog sysLog);

    @Select("select * from syslog ")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "visitTime",column = "visitTime"),
            @Result(property = "ip",column = "ip"),
            @Result(property = "url",column = "url"),
            @Result(property = "executionTime",column = "executionTime"),
            @Result(property = "method",column = "method"),
            @Result(property = "username",column = "username"),
    })
    List<SysLog> findAll() throws Exception;
}
