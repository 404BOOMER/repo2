package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {
    //查询与role关联的所有的权限
    @Select("select * from permission where id in (select permissionId from role_permission where roleId =#{roleId})")
    List<Permission> findPermissionsByRoleId(String roleId) throws Exception;

    //查询所有
    @Select("select * from permission ")
    List<Permission> findAll() throws Exception;

    //添加
    @Insert("insert into permission (permissionName,url) values (#{permissionName},#{url})")
    void save(Permission permission);
}
