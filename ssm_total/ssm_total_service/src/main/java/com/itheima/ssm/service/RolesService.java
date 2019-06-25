package com.itheima.ssm.service;

import com.itheima.ssm.domain.Role;

import java.util.List;

public interface RolesService {
    List<Role> findAll() throws Exception;

    void save(Role role) throws Exception;
}
