package com.gdyiko.tool;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
  
import com.gdyiko.zcwx.po.resp.Article;
import com.gdyiko.zcwx.po.resp.Messages;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
  
/** 
 * Jaxb2工具类 
 * @author      zhuc 
 * @create      2013-3-29 下午2:40:14 
 */  
public class JaxbUtil {  
  
	/** 
     * 将XML转换为Bean 
     * 
     * @param clazzMap 别名-类名映射Map 
     * @param xml      要转换为bean对象的xml字符串 
     * @return Java Bean对象 
     */ 
    @SuppressWarnings("unchecked")
	public static Object xml2Bean(Map<String, Class<?>> clazzMap, String xml) { 
        XStream xstream = new XStream(); 
        for (Iterator it = clazzMap.entrySet().iterator(); it.hasNext();) { 
            Map.Entry<String, Class<?>> m = (Map.Entry<String, Class<?>>) it.next(); 
            xstream.alias(m.getKey(), m.getValue()); 
        } 
        Object bean = xstream.fromXML(xml); 
        return bean; 
    } 
    
    public static void main(String[] args) throws DocumentException {   
        //javabean 转 xml    
        List<Article> users = new ArrayList<Article>();   
        Article art = new Article();
        Messages msg = new Messages(); 
        //xml转javabean    
        XStream xs1 = new XStream(new DomDriver());   
        xs1.alias("Messages", Messages.class);   
        xs1.alias("Message", Article.class);   
        xs1.aliasField("Message", Messages.class, "Messages"); 
        
        SAXReader sax=new SAXReader();//创建一个SAXReader对象  
		 File xmlFile=new File("src/Messages.xml");//根据指定的路径创建file对象  
		 Document document=sax.read(xmlFile);//获取document对象,如果文档无节点，则会抛出Exception提前结束
		 String documentStr = document.asXML(); 
		 System.out.println(documentStr);
        
        art=(Article)xs1.fromXML(documentStr);   
        //users=msg.getArticleList();   
        System.out.println("xml转成javabean为:");   
        for(Article u:users){   
            System.out.println(u.toString());   
        }   
    } 
}