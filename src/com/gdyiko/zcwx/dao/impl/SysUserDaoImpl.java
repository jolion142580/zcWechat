package com.gdyiko.zcwx.dao.impl;

import com.gdyiko.tool.dao.impl.GenericDaoImpl;
import com.gdyiko.zcwx.dao.SysUserDao;
import com.gdyiko.zcwx.po.SysUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("sysUserDao")
public class SysUserDaoImpl extends GenericDaoImpl<SysUser, Long> implements SysUserDao {
    @Override
    public Class<SysUser> getEntityClass() {
        return SysUser.class;
    }
}
