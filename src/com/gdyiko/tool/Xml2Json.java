package com.gdyiko.tool;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.io.SAXReader;  

import com.gdyiko.zcwx.po.resp.Article;

public class Xml2Json {
	
/*	private Object javabean;
	
	public Xml2Json(String classname){
		try {
			this.javabean=Class.forName(classname).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
    /** 
     * 转换一个xml格式的字符串到json格式 
     *  
     * @param file 
     *            java.io.File实例是一个有效的xml文件 
     * @return 成功反回json 格式的字符串;失败反回null 
     */  
    @SuppressWarnings("unchecked")  
    public static StringBuffer xml2JSON(String filename) {  
        JSONObject obj = new JSONObject();  
        try {  
        	SAXReader sax=new SAXReader();//创建一个SAXReader对象  
   		 File xmlFile=new File(filename);//根据指定的路径创建file对象  
   		 Document document=sax.read(xmlFile);//获取document对象,如果文档无节点，则会抛出Exception提前结束
   		 System.out.println(document.getRootElement());
   		Map<String, Object> map = (Map<String, Object>) xml2map(document.getRootElement());
   		System.out.println("1:"+map);
            return new StringBuffer(JSONObject.fromObject(map).toString());
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
    
    
  
    /** 
     * 一个迭代方法 
     *  
     * @param element 
     *            : org.jdom.Element 
     * @return java.util.Map 实例 
     */  
    @SuppressWarnings("unchecked")  
    private static Object xml2map(Element element) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<Element> elements = element.elements();
		if (elements.size() == 0) {
			map.put(element.getName(), element.getText());
			if (!element.isRootElement()) {
				return element.getText();
			}
		} else if (elements.size() == 1) {
			map.put(elements.get(0).getName(), xml2map(elements.get(0)));
		} else if (elements.size() > 1) {
			// 多个子节点的话就得考虑list的情况了，比如多个子节点有节点名称相同的
			// 构造一个map用来去重
			Map<String, Element> tempMap = new HashMap<String, Element>();
			for (Element ele : elements) {
				tempMap.put(ele.getName(), ele);
			}
			Set<String> keySet = tempMap.keySet();
			for (String string : keySet) {
				Namespace namespace = tempMap.get(string).getNamespace();
				List<Element> elements2 = element.elements(new QName(string, namespace));
				// 如果同名的数目大于1则表示要构建list
				if (elements2.size() > 1) {
					List<Object> list = new ArrayList<Object>();
					for (Element ele : elements2) {
						list.add(xml2map(ele));
					}
					map.put(string, list);
				} else {
					// 同名的数量不大于1则直接递归去
					map.put(string, xml2map(elements2.get(0)));
				}
			}
		}

		return map;
	} 
    

    
    @SuppressWarnings("unchecked")
	public static List<Article> toLists(String filename){
    	
    	
		try {
			
			SAXReader sax=new SAXReader();//创建一个SAXReader对象  
	  		 File xmlFile=new File(filename);//根据指定的路径创建file对象  
	  		 Document document;
			document = sax.read(xmlFile);
			Map<String, Object> map = (Map<String, Object>) xml2map(document.getRootElement());
			List<Article> list = new ArrayList<Article>();
			list.add((Article) map.values());
			return list;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}//获取document对象,如果文档无节点，则会抛出Exception提前结束

    }
    
    /** 
     * 将map转换成Javabean 
     * 
     * @param javabean javaBean 
     * @param data map数据 
     */ 
    public static Object toJavaBean(Object javabean, Map<String, String> data) 
    { 
        Method[] methods = javabean.getClass().getDeclaredMethods(); 
        for (Method method : methods) 
        { 
            try 
            { 
                if (method.getName().startsWith("set")) 
                { 
                    String field = method.getName(); 
                    field = field.substring(field.indexOf("set") + 3); 
                    field = field.toLowerCase().charAt(0) + field.substring(1); 
                    method.invoke(javabean, new Object[] 
                    { 
                        data.get(field) 
                    }); 
                } 
            } 
            catch (Exception e) 
            { 
            } 
        } 

        return javabean; 
    } 
	
/*	public static void main(String[] args) throws Exception  {
//		File fl = new File("src/Menu.xml");
		 String json = Xml2Json.xml2JSON("src/Menu2.xml").toString();
         System.out.println(json);
		  
	}*/
}
