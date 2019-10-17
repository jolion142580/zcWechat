package com.gdyiko.zcwx.weixinUtils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;  
import org.apache.axis2.client.ServiceClient;  
import org.apache.axis2.rpc.client.RPCServiceClient;

//import com.sun.org.apache.xpath.internal.axes.HasPositionalPredChecker;

public class StockQuoteClient {
	
	

	
	/** 
	   * 方法一： 
	   * 应用rpc的方式调用 这种方式就等于远程调用， 
	   * 即通过url定位告诉远程服务器，告知方法名称，参数等， 调用远程服务，得到结果。 
	   * 使用 org.apache.axis2.rpc.client.RPCServiceClient类调用WebService 
	   * 
	    【注】： 
	     
	        如果被调用的WebService方法有返回值 应使用 invokeBlocking 方法 该方法有三个参数 
	          第一个参数的类型是QName对象，表示要调用的方法名； 
	          第二个参数表示要调用的WebService方法的参数值，参数类型为Object[]； 
	            当方法没有参数时，invokeBlocking方法的第二个参数值不能是null，而要使用new Object[]{}。 
	          第三个参数表示WebService方法的 返回值类型的Class对象，参数类型为Class[]。 
	         
	         
	        如果被调用的WebService方法没有返回值 应使用 invokeRobust 方法 
	          该方法只有两个参数，它们的含义与invokeBlocking方法的前两个参数的含义相同。 
	 
	        在创建QName对象时，QName类的构造方法的第一个参数表示WSDL文件的命名空间名， 
	        也就是 <wsdl:definitions>元素的targetNamespace属性值。 
	   * 
	   */  
	  public static void testRPCClient() {  
	    try {  
	      // axis1 服务端  
	// String url = "http://localhost:8080/StockQuote/services/StockQuoteServiceSOAP11port?wsdl";  
	      // axis2 服务端  
	      String url = "http://19.133.104.108/axis2/services/TerminalRuiZhiService?wsdl";  
	  
	      // 使用RPC方式调用WebService  
	      RPCServiceClient serviceClient = new RPCServiceClient();  
	      // 指定调用WebService的URL  
	      EndpointReference targetEPR = new EndpointReference(url);  
	      Options options = serviceClient.getOptions();  
	      //确定目标服务地址  
	      options.setTo(targetEPR);  
	      //确定调用方法  
	      options.setAction("urn:QueryAuditingProcessInfoById");  
	  
	      /** 
	       * 指定要调用的getPrice方法及WSDL文件的命名空间 
	       * 如果 webservice 服务端由axis2编写 
	       * 命名空间 不一致导致的问题 
	       * org.apache.axis2.AxisFault: java.lang.RuntimeException: Unexpected subelement arg0 
	       */  
	      QName qname = new QName("http://imp.ruizhi.tjsoft.com", "QueryAuditingProcessInfoById");  
	      // 指定getPrice方法的参数值  
	      String[] parameters = new String[] { "1405261600OM96N" };  
	        
	      // 指定getPrice方法返回值的数据类型的Class对象  
	      Class[] returnTypes = new Class[] { double.class };  
	  
	      // 调用方法一 传递参数，调用服务，获取服务返回结果集  
	      OMElement element = serviceClient.invokeBlocking(qname, parameters);  
	      //值得注意的是，返回结果就是一段由OMElement对象封装的xml字符串。  
	      //我们可以对之灵活应用,下面我取第一个元素值，并打印之。因为调用的方法返回一个结果  
	      String result = element.getText();
	      //System.out.println("结果：："+result);  
	      Map<String, String> map = getResults(element);
//	      System.out.println(map);
//		  System.out.println(map.get("acceptName"));
	  
	  
	    } catch (AxisFault e) {  
	      e.printStackTrace();  
	    }  
	  }
	  
	  
	  public static  Map<String, String> getResults(OMElement element) {
		  
	        if (element == null) {
	            return null;
	        }
//	        System.out.println("IN: " + element); // 新增
	        Iterator iterator = element.getChildElements();
	        Iterator innerItr;
	        Map<String, String> map = new HashMap<String, String>();
	        OMElement result = null;
	        while (iterator.hasNext()) {
	            result = (OMElement) iterator.next();
//	            System.out.println( result); // 新增
	            innerItr = result.getChildElements();
	               while (innerItr.hasNext()) {  // 新增
					  OMElement elem = (OMElement)innerItr.next();  // 新增
//					  System.out.println(elem.getLocalName() + ": " + elem.getText());  // 新增
					  map.put(elem.getLocalName(), elem.getText());
					} // 新增
			}
	        return map;
	}
	
	
}
