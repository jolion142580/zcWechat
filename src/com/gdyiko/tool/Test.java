package com.gdyiko.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi2.hssf.record.formula.functions.T;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.gdyiko.zcwx.po.resp.Article;
import com.thoughtworks.xstream.XStream;


public class Test {
	public T xmlToBean(String xml,Class classType) throws DocumentException, JAXBException{
		
		SAXReader sax=new SAXReader();//创建一个SAXReader对象  
		 File xmlFile=new File(xml);//根据指定的路径创建file对象  
//		 Document document=sax.read(xmlFile);//获取document对象,如果文档无节点，则会抛出Exception提前结束
		T t = null;
		
			JAXBContext context = JAXBContext.newInstance(classType);
			Unmarshaller unMarshaller = context.createUnmarshaller();
			t = (T)unMarshaller.unmarshal(xmlFile);
			System.out.println(t);
	
		return t;
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		/*StringBuffer jsonMenu = new StringBuffer();
		jsonMenu=Xml2Json.xml2JSON("src/Messages.xml");
		System.out.println(jsonMenu.toString());
		
		JSONObject jsonObject =  JSONObject.fromObject(jsonMenu.toString());   
        System.out.println(jsonObject.getString("Message")); 
        JSONArray jsonarry = JSONArray.fromObject(jsonObject);
        JSONArray json = (JSONArray) jsonObject.get("Message");
        System.out.println(json.toString());
        System.out.println(json.toList(json, Article.class));*/
//		Object javabean = Xml2Json.javabean("com.gdyiko.zcwx.po.resp.Article");
//		Article art = new Article();
		SAXReader sax=new SAXReader();//创建一个SAXReader对象  
		 File xmlFile=new File("src/Messages.xml");//根据指定的路径创建file对象  
		 Document document=sax.read(xmlFile);//获取document对象,如果文档无节点，则会抛出Exception提前结束
//		 Document doc = DocumentHelper.parseText(textFromFile);
		  String xml =document.asXML();
		 XStream xstream = new XStream(); 
        List<Article> list =  (List<Article>) xstream.fromXML(xml);
        System.out.println(list.toString());
          
	}

}
