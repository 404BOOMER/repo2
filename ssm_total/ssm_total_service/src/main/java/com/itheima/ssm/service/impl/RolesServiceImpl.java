package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.RolesDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RolesServiceImpl implements RolesService {
    @Autowired
    private RolesDao rolesDao;

    //查询所有角色
    @Override
    public List<Role> findAll() throws Exception {
        return rolesDao.findAll();
    }

    //添加角色
    @Override
    public void save(Role role) throws Exception {
        rolesDao.save(role);
    }
}
