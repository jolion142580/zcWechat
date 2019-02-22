package com.gdyiko.zcwx.action;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springside.modules.web.struts2.Struts2Utils;

import com.gdyiko.zcwx.po.SsBaseDic;
import com.gdyiko.zcwx.po.Street;
import com.gdyiko.zcwx.po.WordBook;
import com.gdyiko.zcwx.service.InterfaceService;
import com.gdyiko.zcwx.service.WordBookService;
import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.action.BaseAction;
import com.gdyiko.tool.service.GenericService;

//@ParentPackage("custom-default")
@Namespace("/")
@Action(value = "WordBook", results = {
//成功
		@Result(name = "success", location = "/choiceDep.jsp"),

		// 失败
		@Result(name = "fail", location = "/")

})
//interceptorRefs = {@InterceptorRef(value = "mydefault")})
public class WordBookAction  extends BaseAction<WordBook, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3192529103949634800L;

	public WordBookAction() {
		
	}
	
	private List<WordBook> wordBookList;
	
	@Resource(name = "interfaceService")
	InterfaceService ifs;

	@Resource(name = "wordBookService")
	WordBookService wordBookService;

	@Resource(name = "wordBookService")
	@Override
	public void setGenericService(GenericService<WordBook, String> genericService) {
		// TODO Auto-generated method stub
		super.setGenericService(genericService);
	}
	
	public String findByWordBook(){
		this.model.setType("department");
		wordBookList = wordBookService.findLikeByEntity(this.model, BeanUtilEx.getNotNullEscapePropertyNames(this.model));
		//JSONArray ja = JSONArray.fromObject(wordBookList);
		//Struts2Utils.renderText(ja.toString());
		return "success";
		
	}

	public List<WordBook> getWordBookList() {
		return wordBookList;
	}

	public void setWordBookList(List<WordBook> wordBookList) {
		this.wordBookList = wordBookList;
	}

}
