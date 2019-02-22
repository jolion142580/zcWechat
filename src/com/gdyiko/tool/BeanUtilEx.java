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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class BeanUtilEx {

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
	
	public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());

            if (srcValue == null){ emptyNames.add(pd.getName());
            
            }
            //更新时候不清理关系
            if(src.getPropertyType(pd.getName()).isAssignableFrom(java.util.Set.class)){
            	
            	 emptyNames.add(pd.getName());
            }
            ///src.getPropertyType(pd.getName()).isAssignableFrom(java.util.Set.class);
            /*if("[]".equals(srcValue.toString())){
            	
            	
            }*/
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
	
	//不设置非空逃逸，即查询条件可以为null
	public static String[] getNotNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue != null && "class".equals(pd.getName())==false ) 
            	{
            		emptyNames.add(pd.getName());
            		System.out.println(pd.getName());
            	}
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
	
	//设置非空逃逸，即查询条件可以是"",但不能为null
	public static String[] getNotNullEscapePropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue != null&& "".equals(srcValue)==false && "class".equals(pd.getName())==false ) 
            	{
            		emptyNames.add(pd.getName());
            		System.out.println(pd.getName());
            	}
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
	public static void main(String[] args) {

		
	}


}
