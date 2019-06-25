package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.SysLogDao;
import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;

    //添加日志信息到数据库
    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }

    //查询所有日志信息
    @Override
    public List<SysLog> findAll() throws Exception {

        return sysLogDao.findAll();
    }
}
