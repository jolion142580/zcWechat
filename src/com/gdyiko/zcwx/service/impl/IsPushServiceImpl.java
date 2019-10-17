package com.gdyiko.zcwx.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdyiko.zcwx.dao.IsPushDao;
import com.gdyiko.zcwx.po.IsPush;
import com.gdyiko.zcwx.service.IsPushService;
import com.gdyiko.tool.PrimaryProduce;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;

import freemarker.core.Comment;

@Service("isPushService")
public class IsPushServiceImpl extends GenericServiceImpl<IsPush, String> implements IsPushService
{
	@Resource(name = "isPushDao")
	private IsPushDao isPushDao;

	@Resource(name = "isPushDao")
	@Override
	public void setGenericDao(GenericDao<IsPush, String> genericDao) {
		super.setGenericDao(genericDao);
	}
	
	/**
	 * 判断指定的预约号是否已经推送
	 * 
	 * @param yyno	预约号码
	 * 
	 * @return	已经推送,返回true;否则返回false;
	 * 
	 * @throws ParseException
	 */
	public boolean yynoIsPush(String yyno) throws ParseException
	{
		if( yyno != null && "".equals(yyno.trim()) )
		{
			throw new RuntimeException("预约号码不能为空");
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentTimeStr = sdf.format(new Date());
		Date currentTime = sdf.parse(currentTimeStr);
		
		List<IsPush> list = this.isPushDao.findByYyno(yyno);
		
		if( list.size() > 0 )
		{
			return true;
		}
		else
		{
			IsPush isPush = new IsPush();
			isPush.setId(PrimaryProduce.produce());
		    isPush.setYyno(yyno);
			isPush.setPushTime(currentTime);
			
			this.isPushDao.save(isPush);
			
			return false;
		}
	}
	
}
