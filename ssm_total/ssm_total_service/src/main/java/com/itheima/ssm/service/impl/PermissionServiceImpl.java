package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.PermissionDao;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    //查询所有
    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    //添加
    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }
}
