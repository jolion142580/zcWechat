package com.gdyiko.zcwx.service.impl;

import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;
import com.gdyiko.zcwx.dao.SysUserDao;
import com.gdyiko.zcwx.po.SysUser;
import com.gdyiko.zcwx.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.naming.Name;

@Service("sysUserService")
public class SysUserServiceImpl extends GenericServiceImpl<SysUser, Long> implements SysUserService {

    @Resource(name = "sysUserDao")
    SysUserDao sysUserDao;

    @Resource(name = "sysUserDao")
    @Override
    public void setGenericDao(GenericDao<SysUser, Long> genericDao) {
        super.setGenericDao(genericDao);
    }
}
