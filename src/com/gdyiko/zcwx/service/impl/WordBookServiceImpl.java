package com.gdyiko.zcwx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdyiko.zcwx.dao.WordBookDao;
import com.gdyiko.zcwx.po.WordBook;
import com.gdyiko.zcwx.service.WordBookService;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;

@Service("wordBookService")
public class WordBookServiceImpl extends GenericServiceImpl<WordBook, String> implements WordBookService {
	@Resource(name = "wordBookDao")
	WordBookDao wordBookDao;

	@Resource(name = "wordBookDao")
	@Override
	public void setGenericDao(GenericDao<WordBook, String> genericDao) {
		super.setGenericDao(genericDao);
	}

}
