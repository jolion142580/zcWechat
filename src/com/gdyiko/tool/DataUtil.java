package com.gdyiko.tool;

import java.beans.XMLDecoder;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.gdyiko.common.helper.AttrOrderable;



public class DataUtil {
	
	/**
	 * 分割符"_"
	 */
	public static final String DELIMITER_INNER_ITEM = "_";
	/**
	 * 分割符"|"
	 */
	public static final String DELIMITER_BETWEEN_ITEMS = "|";
	public static final String ARGS_ITEMS_DELIMITER = "\\|";
	
	/**
	 * 从xml中读取对象到Map
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static <T,V> Map<V, T> readXml2KeyMap(InputStream in,String keyName) throws Exception {
		Map<V, T> model = new HashMap<V, T>();
		XMLDecoder decoder = new XMLDecoder(in);
		T obj = (T) decoder.readObject();
		while (obj != null) {
			try {
				model.put((V)BeanUtil.getBeanProperty(obj, keyName), obj);
				obj = (T)decoder.readObject();
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}
		decoder.close();
		in.close();
		return model;
	}
	
	
	/**
	 * 根据xml对象创建名值的map
	 * 
	 * @param list
	 * @return
	 * @throws DocumentException 
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> readXml2Map(InputStream io) throws Exception {
		Map<String, String> model = new HashMap<String, String>();
		SAXReader reader = new  SAXReader();
		Document document = reader.read(io);
		Element rootElement = document.getRootElement();
		Iterator<Element> iterator = rootElement.elementIterator();
		while (iterator.hasNext()) {
			Element objectElement = iterator.next();
			model.put(objectElement.attributeValue("variable"), objectElement.attributeValue("value"));			
			//System.out.println(objectElement.attributeValue("value"));
		}
		io.close();
		return model;
	}
	
	/**
	 * 将字符串解释成Map<String,AttrOrderable>
	 * 
	 * @param str 目标解析字符串
	 * @param a 实例化对象
	 * @return Map<String,AttrOrderable>
	 * @throws Exception 
	 */
	public static Map<String, AttrOrderable> delimiterString2Map(String str,AttrOrderable a) throws Exception {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(ARGS_ITEMS_DELIMITER);
			Map<String, AttrOrderable> map = new HashMap<String, AttrOrderable>(arr.length);			
			for (int i=0;i<arr.length;i++) {
				AttrOrderable b =  (AttrOrderable) BeanUtil.deepClone(a);
				String s = arr[i];
				String[] subArr = s.split(DELIMITER_INNER_ITEM);				
				for (int j = 0; j < subArr.length; j++) {
					b.setAttrNameByOrder(j, subArr[j]);					
				}
				map.put(subArr[0], b);
			}
			return map;
		} else {
			return null;
		}
	}
	
	
	/**
	 * LIST 去重复 
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Long> removeDuplicateObj(List<Long> list)   
	{ 
	     Set<Long> someSet = new HashSet<Long>(list);    
	     Iterator<Long> iterator = someSet.iterator();      
	     List<Long> tempList = new ArrayList<Long>();      
	     int i = 0;      
	     while(iterator.hasNext()){                  
	       tempList.add(iterator.next());
	       i++;      
	     }      
	     return tempList;      
	} 

	
	public static void main(String[] args) throws Exception {
		String aaa= "safsaf|dsd_sad_SA";
		String aa[]=aaa.split("\\|");
		
		for(int i=0;i<aa.length;i++){
			System.out.println(aa[i]);
		}
		readXml2Map(DataUtil.class.getClassLoader().getResourceAsStream("basexml/StarDepart.xml"));
	}

}
