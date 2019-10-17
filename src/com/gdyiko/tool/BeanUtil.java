package com.gdyiko.tool;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class BeanUtil {

	/**
     * 深克隆方法
	 * @return
	 */
	public static Object deepClone(Object o) throws Exception {
		// 首先将对象写到流里
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(o);

		// 然后将对象从流里读出来
		ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
		ObjectInputStream oi = new ObjectInputStream(bi);

		return oi.readObject();
	}
	
	public static void initBeanProperty(Object bean,Map<String,Object> param) throws Exception {
		BeanInfo bi = Introspector.getBeanInfo(bean.getClass());
		PropertyDescriptor[] pds = bi.getPropertyDescriptors();
		for (int i = 0; i < pds.length; i++) {
			String name = pds[i].getName();
			String strValue = "";
			Object value = param.get(name);
			if (value != null) {
				Method method = pds[i].getWriteMethod();
				value = convert(strValue, method.getParameterTypes()[0]);
				method.invoke(bean, new Object[] {value});
			}
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static <E,T> E getBeanProperty(T bean,String var) throws Exception {
		Field fd = bean.getClass().getDeclaredField(var);
		fd.setAccessible(true);//设置允许访问
		return (E)fd.get(bean);
	}
	
	public static void setBeanProperty(Object bean,String var, Object value) throws Exception {
		BeanInfo bi = Introspector.getBeanInfo(bean.getClass());
		PropertyDescriptor[] pds = bi.getPropertyDescriptors();
		for (int i = 0; i < pds.length; i++) {
			String name = pds[i].getName();
			if(name.equals(var)){
				String strValue = "";
				if (value != null) {
					Method method = pds[i].getWriteMethod();
					value = convert(strValue, method.getParameterTypes()[0]);
					method.invoke(bean, new Object[] {value});
				}
				break;
			}		
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static Object convert(Object value, Class type) {
	    String name = type.getName();
		if ("java.lang.String".equals(name)) {
			return value.toString();
		} else if ("java.lang.Long".equals(name) || (type == Long.TYPE)) {
			return Long.valueOf(value.toString());
		} else if ("java.lang.Integer".equals(name) || (type == Integer.TYPE)) {
			return Integer.valueOf(value.toString());
		} else {
			return null;
		}
	}
	
	public static Object convert(Object value, String type) {
	    String name = type;
		if ("String".equals(name)) {
			return value.toString();
		} else if ("Long".equals(name)) {
			return Long.valueOf(value.toString());
		} else if ("Integer".equals(name)) {
			return Integer.valueOf(value.toString());
		} else {
			return null;
		}
	}
}
