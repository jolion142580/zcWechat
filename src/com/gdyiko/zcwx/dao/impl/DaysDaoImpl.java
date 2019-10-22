package com.gdyiko.zcwx.dao.impl;

import java.util.Calendar;
import java.util.List;

import com.gdyiko.zcwx.dao.DaysDao;
import com.gdyiko.zcwx.po.Days;
import org.springframework.stereotype.Repository;

import com.gdyiko.tool.dao.impl.GenericDaoImpl;

@Repository("daysDao")
public class DaysDaoImpl extends GenericDaoImpl<Days, String> implements DaysDao {

	@Override
	public Class<Days> getEntityClass() {
		// TODO Auto-generated method stub
		return Days.class;
	}

	public Days findPkey(String name) {
		 Calendar a = Calendar.getInstance();
		String year = String.valueOf(a.get(Calendar.YEAR));
		List<Days> list = findByHqlParam("from Days where name=? and year=?", new String[]{name,year});
		if(list.size()>0)
			return list.get(0);
		return null;
	}

}
