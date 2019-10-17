package com.gdyiko.base.service;

import com.gdyiko.base.po.MemGeninf;

public interface LoginService {
	public MemGeninf loginVerify(String userName,String password);
}
