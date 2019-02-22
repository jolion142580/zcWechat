package com.gdyiko.tool;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import com.game.commons.util.BeanUtil;


/**
 * xml工具类
 * 
 * @author pvsp
 * 
 */
public class XmlUtil {

	private static Log log = LogFactory.getLog(XmlUtil.class);

	/**
	 * 将对象列表输出成xml文件
	 * 
	 * @param list
	 *            对象列表
	 * @param fos
	 *            输出流
	 * @throws IOException
	 */
	public static void writeList2Xml(Collection<?> list, OutputStream os)
			throws IOException {
		XMLEncoder encoder = new XMLEncoder(os);
		for (Object obj : list) {
			encoder.writeObject(obj);
		}
		encoder.flush();
		encoder.close();
		os.close();
	}

	

	/**
	 * 输出xml文件
	 * 
	 * @param document
	 * @param out
	 */
	public static void writeDocument(Document document, OutputStream out) {
		try {
			OutputFormat format = new OutputFormat();
			format.setEncoding("utf-8");
			XMLWriter writer = new XMLWriter(new OutputStreamWriter(out,
					"utf-8"), format);
			writer.write(document);
			writer.close();
		} catch (Exception e) {
			log.error("输出xml文件有错", e);
		}
	}

	/**
	 * 把dom4j的Document转为amf3编码
	 * 
	 * @param document
	 * @return
	 */
	private static byte[] documentToByte(Document document) {
		try {
			byte[] temp = null;
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			OutputFormat format = new OutputFormat();
			XMLWriter writer = new XMLWriter(byteOut, format);
			writer.write(document);
			writer.close();
			temp = byteOut.toByteArray();
			byteOut.close();
			return temp;
		} catch (Exception e) {
			log.error("Document解释成amf3有错", e);
			return null;
		}
	}

	/**
	 * 根据数据列表创建xml对象
	 * 
	 * @param list
	 * @return
	 */
	public static Document createDocument(Collection<?> list) {
		Document document = DocumentHelper.createDocument();
		if (CollectionUtils.isEmpty(list)) {
			return document;
		}
		Element root = document.addElement("root");
		List<String> properties = null;
		for (Object object : list) {
			if (properties == null) {
				properties = BeanUtil.getProperties(object.getClass());
			}
			Element data = root.addElement("data");
			for (String property : properties) {
				Object value = BeanUtil.getProperty(object, property);
				if (value == null) {
					value = "";
				}
				data.addElement(property).addText(value + "");
			}
		}
		return document;
	}
	
}