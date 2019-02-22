package com.gdyiko.tool;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.io.SAXReader;

import com.gdyiko.zcwx.po.resp.Article;
import com.gdyiko.zcwx.po.resp.Messages;


public class Test2 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws UnsupportedEncodingException, DocumentException {
//		String textFromFile = MyTextUtil.getTextFromFile(new File("src/tt.xml"));
//		Document doc = DocumentHelper.parseText(textFromFile);
		SAXReader sax=new SAXReader();//创建一个SAXReader对象  
		 File xmlFile=new File("src/Messages.xml");//根据指定的路径创建file对象  
		 Document document=sax.read(xmlFile);//获取document对象,如果文档无节点，则会抛出Exception提前结束
//		 Document doc = DocumentHelper.parseText(textFromFile);
		 System.out.println("-----"+document.asXML());
		Map<String, Object> map = (Map<String, Object>) xml2map(document.getRootElement());
		JSONObject json = JSONObject.fromObject(map);
		System.out.println(map.values());
		List<Article> list = new ArrayList<Article>();
		list.add((Article) map.values());
		Messages msg = new Messages();
		msg.setArticle(list);
		System.out.println(msg.getArticle());
		
		/*try {
			msg =  (Messages) Test2.convertMap(Messages.class,map);
			System.out.println(msg.getArticle());
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
//		JSONObject jsonobj = json.getJSONObject(0).getJSONObject("Message");  
//		System.out.println(jsonobj.toString());
		/*List<Article> list = (List<Article>)JSONArray.toCollection(jsonobj, Article.class);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getTitle());
		}*/
	}

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
	
	 public static Object convertMap(Class type, Map map)  
	            throws IntrospectionException, IllegalAccessException,  
	            InstantiationException, InvocationTargetException {  
	        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性  
	        Object obj = type.newInstance(); // 创建 JavaBean 对象  
	  
	        // 给 JavaBean 对象的属性赋值  
	        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();  
	        for (int i = 0; i< propertyDescriptors.length; i++) {  
	            PropertyDescriptor descriptor = propertyDescriptors[i];  
	            String propertyName = descriptor.getName();  
	  
	            if (map.containsKey(propertyName)) {  
	                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。  
	                Object value = map.get(propertyName);  
	  
	                Object[] args = new Object[1];  
	                args[0] = value;  
	  
	                descriptor.getWriteMethod().invoke(obj, args);  
	            }  
	        }  
	        return obj;  
	    }  
 
}