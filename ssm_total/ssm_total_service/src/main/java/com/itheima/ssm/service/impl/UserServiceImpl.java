package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.UserDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.UserService;
import com.itheima.ssm.utils.BcryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    //登录
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Role> roles = userInfo.getRoles();
        List<SimpleGrantedAuthority> authoritys = getAuthority(roles);
        User user = new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true, true, true, true, authoritys);
        return user;
    }

    //获得集合的方法
    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;

    }

    //查询所有用户
    @Override
    public List<UserInfo> findAll() throws Exception {

        return userDao.findAll();
    }

    //添加用户
    @Override
    public void save(UserInfo userInfo) throws Exception {
        userInfo.setPassword(BcryptUtils.encoder(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    //查询详情
    @Override
    public UserInfo findById(String id) throws Exception {

        return userDao.findById(id);
    }

    //根据用户id查询未拥有的角色
    @Override
    public List<Role> findOtherRoles(String userId) throws Exception {

        return userDao.findOtherRoles(userId);
    }

    //对用户添加角色
    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        if (roleIds.length != 0 && roleIds != null) {
            for (String roleId : roleIds) {
                userDao.addRoleToUser(userId, roleId);
            }
        }
    }
}
