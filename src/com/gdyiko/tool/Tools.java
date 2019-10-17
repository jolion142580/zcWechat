package com.gdyiko.tool;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;

import com.game.commons.util.DateUtil;

/**
 * @author jamlin
 * 
 */
public class Tools {

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
	 * 分割符"\\|"
	 */
	public static final String DELIMITER_ARGS_ITEMS = "\\|";
    
	public static final String DELIMITER_COMMA = ",";
	/**
	 * 把字符串转换为基本类型对象（String、Integer、Short、Double、Float） 前提条件：字符串是数字
	 * 
	 * @param str
	 * @param c
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T stringNumber2BasicTypes(String str, Class<T> c) {
		if (String.class.equals(c)) {
			return (T) str;
		} else if (Integer.class.equals(c)) {
			return (T) Integer.valueOf(str);
		} else if (Long.class.equals(c)) {
			return (T) Long.valueOf(str);
		} else if (Double.class.equals(c)) {
			return (T) Double.valueOf(str);
		} else if (Float.class.equals(c)) {
			return (T) Float.valueOf(str);
		} else if (Short.class.equals(c)) {
			return (T) Short.valueOf(str);
		} else {
			throw new IllegalArgumentException("无效的类型：" + c.getSimpleName());
		}
	}
	/**
	 * 构造字符串,格式为：a|b|c
	 * 
	 * @param object
	 * @return
	 */
	public static String constructString(Object... object) {
		StringBuilder bf = null;
		for (Object obj : object) {
			if (obj != null && !obj.equals("")) {
				if (bf == null) {
					bf = new StringBuilder();
				} else {
					bf.append(DELIMITER_BETWEEN_ITEMS);
				}
				bf.append(obj);
			}
		}
		return bf == null ? null : bf.toString();
	}

	/**
	 * 构造字符串,格式为：a,b,c
	 * 
	 * @param object
	 * @return
	 */
	public static String constructStringByCas(List<String> list) {
		StringBuilder bf = null;
		for (String s : list) {
			if (s != null && !s.equals("")) {
				if (bf == null) {
					bf = new StringBuilder();
				} else {
					bf.append(",");
				}
				bf.append(s);
			}
		}
		return bf == null ? null : bf.toString();
	}
    public static String constructStringBySql(List<String> list){
    	StringBuilder bf = null;
    	for (String s : list) {
			if(s!=null&!s.equals("")){
				if(bf==null){
					bf = new StringBuilder();
				}else{
					bf.append(",");
				}
				bf.append(" '"+s+"' ");
			}
		}
    	return bf.toString();
    }
	/**
	 * 把以,分割的字符串分解成List<String>
	 * 
	 * @param object
	 * @return
	 */
	public static List<String> delimiterStringToListByCas(String str) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(",");
			List<String> list = null;
			for (String s : arr) {
				if (CollectionUtils.isEmpty(list)) {
					list = new ArrayList<String>(arr.length);
				}
				list.add(s);
			}
			return list;
		} else {
			return null;
		}
	}

	/**
	 * 把以,分割的字符串分解成List<String>
	 * 
	 * @param object
	 * @return
	 */
	public static List<Integer> delimiterStringToIntegerListByCas(String str) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(",");
			List<Integer> list = null;
			for (String s : arr) {
				if (CollectionUtils.isEmpty(list)) {
					list = new ArrayList<Integer>(arr.length);
				}
				list.add(Integer.parseInt(s.trim()));
			}
			return list;
		} else {
			return null;
		}
	}
	
	/**
	 * 把以|分割的字符串分解成List<String>
	 * 
	 * @param str
	 * @return
	 */
	public static List<String> delimiterString2List(String str) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(ARGS_ITEMS_DELIMITER);
			List<String> list = null;
			for (String s : arr) {
				if (CollectionUtils.isEmpty(list)) {
					list = new ArrayList<String>(arr.length);
				}
				list.add(s);
			}
			return list;
		} else {
			return null;
		}
	}
	public static List<String> delimiterString2List(String str,String delimiter) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(delimiter);
			List<String> list = null;
			for (String s : arr) {
				if (CollectionUtils.isEmpty(list)) {
					list = new ArrayList<String>(arr.length);
				}
				list.add(s);
			}
			return list;
		} else {
			return null;
		}
	}
	/**
	 * 把List<Object>组装成以|分割的字符串
	 * 
	 * @param list
	 * @return
	 */
	public static String delimiterList2String(Collection<?> list) {
		StringBuilder bf = null;
		if (!CollectionUtils.isEmpty(list)) {
			for (Object obj : list) {
				if (obj != null && !obj.equals("")) {
					if (bf == null) {
						bf = new StringBuilder();
					} else {
						bf.append(DELIMITER_BETWEEN_ITEMS);
					}
					bf.append(obj);
				}
			}
		}
		return bf == null ? null : bf.toString();
	}

	/**
	 * 把数组Object[]组装成以|分割的字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String delimiterArray2String(Object[] objs) {
		StringBuilder bf = null;
		if (objs != null && objs.length > 0) {
			for (Object obj : objs) {
				if (obj != null && !obj.equals("")) {
					if (bf == null) {
						bf = new StringBuilder();
					} else {
						bf.append(DELIMITER_BETWEEN_ITEMS);
					}
					bf.append(obj);
				}
			}
		}
		return bf == null ? null : bf.toString();
	}

	/**
	 * 把以|分割的字符串分解成数组String[]
	 * 
	 * @param str
	 * @return
	 */
	public static String[] delimiterString2Array(String str) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(ARGS_ITEMS_DELIMITER);
			return arr;
		} else {
			return null;
		}
	}
	public static String[] delimiterString2Array(String str,String delimiter) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(delimiter);
			return arr;
		} else {
			return null;
		}
	}
	/**
	 * 将类似1_2|3格式转化为List<Long>
	 * 
	 * @param str
	 * @return
	 */
	public static List<Long> divideString2LongList(String str) {
		List<String> list = delimiterString2List(str);
		List<Long> result = new ArrayList<Long>();
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		for (String s : list) {
			if (s.contains("_")) {
				String[] subArr = s.split(DELIMITER_INNER_ITEM);
				if(subArr.length==0){
					subArr = s.split(DELIMITER_COMMA);
				}
				for (String sub : subArr) {
					result.add(Long.parseLong(sub));
				}
				continue;
			}
			result.add(Long.parseLong(s));
		}
		return result;
	}

	/**
	 * 把以|分割的字符串分解成List<Integer>
	 * 
	 * @param str
	 * @return
	 */
	public static List<Integer> delimiterString2IntegerList(String str) {
		String[] arr = delimiterString2Array(str);
		if (arr == null || arr.length == 0) {
			return null;
		}
		List<Integer> result = new ArrayList<Integer>(arr.length);
		for (int i = 0; i < arr.length; i++) {
			result.add(Integer.parseInt(arr[i]));
		}
		return result;
	}

	/**
	 * 把以|分割的字符串分解成List<Integer>
	 * 
	 * @param str
	 * @return
	 */
	public static LinkedList<Integer> delimiterString2IntegerLinkedList(
			String str) {
		String[] arr = delimiterString2Array(str);
		if (arr == null || arr.length == 0) {
			return null;
		}
		LinkedList<Integer> result = new LinkedList<Integer>();
		for (int i = 0; i < arr.length; i++) {
			result.add(Integer.parseInt(arr[i]));
		}
		return result;
	}

	/**
	 * 把以|分割的字符串分解成HashSet<Integer>
	 * 
	 * @param str
	 * @return
	 */
	public static HashSet<Integer> delimiterString2IntegerHashSet(String str) {
		String[] arr = delimiterString2Array(str);
		if (arr == null || arr.length == 0) {
			return null;
		}
		HashSet<Integer> result = new HashSet<Integer>();
		for (int i = 0; i < arr.length; i++) {
			result.add(Integer.parseInt(arr[i]));
		}
		return result;
	}

	/**
	 * 把以|分割的字符串分解成数组Integer[]
	 * 
	 * @param str
	 * @return
	 */
	public static Integer[] delimiterString2IntegerArray(String str) {
		String[] arr = delimiterString2Array(str);
		if (arr == null || arr.length == 0) {
			return null;
		}
		Integer[] result = new Integer[arr.length];
		for (int i = 0; i < arr.length; i++) {
			result[i] = Integer.parseInt(arr[i]);
		}
		return result;
	}

	/**
	 * 把以_分割的字符串分解成数组Integer[]
	 * 
	 * @param str
	 * @return
	 */
	public static Integer[] delimiterString2IntegerArray1(String str) {
		String[] arr = str.split(DELIMITER_INNER_ITEM);
		if (arr == null || arr.length == 0) {
			return null;
		}
		Integer[] result = new Integer[arr.length];
		for (int i = 0; i < arr.length; i++) {
			result[i] = Integer.parseInt(arr[i]);
		}
		return result;
	}

	/**
	 * 把以|分割的字符串分解成数组Long[]
	 * 
	 * @param str
	 * @return
	 */
	public static Long[] delimiterString2LongArray(String str) {
		String[] arr = delimiterString2Array(str);
		if (arr == null || arr.length == 0) {
			return null;
		}
		Long[] result = new Long[arr.length];
		for (int i = 0; i < arr.length; i++) {
			result[i] = Long.parseLong(arr[i]);
		}
		return result;
	}

	/**
	 * 把以|分割的字符串分解成数组Double[]
	 * 
	 * @param str
	 * @return
	 */
	public static Double[] delimiterString2DoubleArray(String str) {
		String[] arr = delimiterString2Array(str);
		if (arr == null || arr.length == 0) {
			return null;
		}
		Double[] result = new Double[arr.length];
		for (int i = 0; i < arr.length; i++) {
			result[i] = Double.parseDouble(arr[i]);
		}
		return result;
	}

	/**
	 * 把以1_1_1|1_3_1类似格式的字符串分解成HashMap<Integer,Double[]><br>
	 * 
	 * @param str
	 * @return
	 */
	public static Map<Integer, Double[]> delimiterString2MapDoubleArray(
			String str) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(ARGS_ITEMS_DELIMITER);
			Map<Integer, Double[]> map = new HashMap<Integer, Double[]>(
					arr.length);
			for (String s : arr) {
				String[] subArr = s.split(DELIMITER_INNER_ITEM);
				Double[] value = new Double[subArr.length - 1];
				for (int i = 0; i < value.length; i++) {
					if (subArr[i + 1].equals("null")) {
						value[i] = 0d;
					} else {
						value[i] = Double.parseDouble(subArr[i + 1]);
					}
				}
				map.put(Integer.parseInt(subArr[0]), value);
			}
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 把HashMap<Integer,Double[]>转为String
	 * 
	 * @param str
	 * @return
	 */
	public static String delimiterMapDoubleArray2String(
			Map<Integer, Double[]> map) {
		if (map == null) {
			return null;
		}
		StringBuffer sb = null;
		for (Map.Entry<Integer, Double[]> entry : map.entrySet()) {
			if (sb == null) {
				sb = new StringBuffer();
			} else {
				sb.append(DELIMITER_BETWEEN_ITEMS);
			}
			sb.append(entry.getKey());
			for (int i = 0; i < entry.getValue().length; i++) {
				sb.append(DELIMITER_INNER_ITEM).append(entry.getValue()[i]);
			}
		}
		return sb == null ? null : sb.toString();
	}

	/**
	 * 把以1_1_1|1_3_1类似格式的字符串分解成HashMap<Integer,Double[]><br>
	 * 
	 * @param str
	 * @return
	 */
	public static Map<Integer, Long[]> delimiterString2MapLongArray(String str) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(ARGS_ITEMS_DELIMITER);
			Map<Integer, Long[]> map = new HashMap<Integer, Long[]>(arr.length);
			for (String s : arr) {
				String[] subArr = s.split(DELIMITER_INNER_ITEM);
				Long[] value = new Long[subArr.length - 1];
				for (int i = 1; i < subArr.length; i++) {
					value[i - 1] = Long.parseLong(subArr[i]);
				}
				map.put(Integer.parseInt(subArr[0]), value);
			}
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 把HashMap<Integer,Long[]>转为String
	 * 
	 * @param str
	 * @return
	 */
	public static String delimiterMapLongArray2String(Map<Integer, Long[]> map) {
		if (map == null) {
			return null;
		}
		StringBuffer sb = null;
		for (Map.Entry<Integer, Long[]> entry : map.entrySet()) {
			if (sb == null) {
				sb = new StringBuffer();
			} else {
				sb.append(DELIMITER_BETWEEN_ITEMS);
			}
			sb.append(entry.getKey());
			for (int i = 0; i < entry.getValue().length; i++) {
				sb.append(DELIMITER_INNER_ITEM).append(entry.getValue()[i]);
			}
		}
		return sb == null ? null : sb.toString();
	}

	/**
	 * 把HashMap<Integer,Integer[]>转为String
	 * 
	 * @param map
	 * @return
	 */
	public static String delimiterMapIntegerArray2String(
			Map<Integer, Integer[]> map) {
		if (map == null) {
			return null;
		}
		StringBuffer sb = null;
		for (Map.Entry<Integer, Integer[]> entry : map.entrySet()) {
			if (sb == null) {
				sb = new StringBuffer();
			} else {
				sb.append(DELIMITER_BETWEEN_ITEMS);
			}
			sb.append(entry.getKey());
			for (int i = 0; i < entry.getValue().length; i++) {
				sb.append(DELIMITER_INNER_ITEM).append(entry.getValue()[i]);
			}
		}
		return sb == null ? null : sb.toString();
	}

	/**
	 * 把以1_2|1_3类似格式的字符串分解成HashMap<Integer,String>
	 * 
	 * @param str
	 * @return
	 */
	public static Map<Integer, String> delimiterString2Integer1Map(String str) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(ARGS_ITEMS_DELIMITER);
			Map<Integer, String> map = new HashMap<Integer, String>(arr.length);
			for (String s : arr) {
				String[] subArr = s.split(DELIMITER_INNER_ITEM);
				map.put(Integer.parseInt(subArr[0]), subArr[1]);
			}
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 把以1_2|1_3类似格式的字符串分解成HashMap<Integer,Object><br>
	 * Object的value可能为Integer或Map
	 * 
	 * @param str
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<Integer, Object> delimiterString2Integer2Map(String str) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(ARGS_ITEMS_DELIMITER);
			Map<Integer, Object> map = new HashMap<Integer, Object>(arr.length);
			for (String s : arr) {
				String[] subArr = s.split(DELIMITER_INNER_ITEM);
				Integer subKey = Integer.parseInt(subArr[0]);
				if (subArr.length == 3) {
					Map<Integer, Object> subMap = (Map<Integer, Object>) map
							.get(subKey);
					if (subMap == null) {
						subMap = new HashMap<Integer, Object>();
						map.put(subKey, subMap);
					}
					subMap.put(Integer.parseInt(subArr[1]), Integer
							.parseInt(subArr[2]));
				} else if (subArr.length == 2) {
					map.put(Integer.parseInt(subArr[0]), Integer
							.parseInt(subArr[1]));
				}
			}
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 把以1_2|1_3类似格式的字符串分解成HashMap<Integer,Long>
	 * 
	 * @param str
	 * @return
	 */
	public static Map<Integer, Long> delimiterString2Integer3Map(String str) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(ARGS_ITEMS_DELIMITER);
			Map<Integer, Long> map = new HashMap<Integer, Long>(arr.length);
			for (String s : arr) {
				String[] subArr = s.split(DELIMITER_INNER_ITEM);
				map.put(Integer.parseInt(subArr[0]), Long.parseLong(subArr[1]));
			}
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 把以1_2|2_3|3_5_6_7类似格式的字符串分解成HashMap<Integer,String>
	 * 
	 * @param str
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<Integer, String> delimiterString2Integer4Map(String str) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(ARGS_ITEMS_DELIMITER);
			Map<Integer, String> map = new HashMap<Integer, String>(arr.length);
			for (String s : arr) {
				String[] subArr = s.split(DELIMITER_INNER_ITEM);
				Integer subKey = Integer.parseInt(subArr[0]);
				List itemList = new ArrayList<String>(subArr.length - 1);
				for (int i = 1; i < subArr.length; i++) {
					itemList.add(subArr[i]);
				}
				map.put(subKey, delimiterList2String(itemList));
			}
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 把以1_2_3|2_3_4|3_5_6_7类似格式的字符串分解成HashMap<Integer,Integer[]>
	 * 
	 * @param str
	 * @return
	 */
	public static Map<Integer, Integer[]> delimiterString2Integer5Map(String str) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(ARGS_ITEMS_DELIMITER);
			Map<Integer, Integer[]> map = new HashMap<Integer, Integer[]>(
					arr.length);
			for (String s : arr) {
				String[] subArr = s.split(DELIMITER_INNER_ITEM);
				Integer subKey = Integer.parseInt(subArr[0]);
				Integer[] itemArray = new Integer[subArr.length - 1];
				for (int i = 1; i < subArr.length; i++) {
					itemArray[i - 1] = Integer.parseInt(subArr[i]);
				}
				map.put(subKey, itemArray);
			}
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 把以1_2_3|2_3_4|3_5_6_7类似格式的字符串分解成HashMap<Integer,Float[]>
	 * 
	 * @param str
	 * @return
	 */
	public static Map<Integer, Float[]> delimiterString2IntegerFloatArrayMap(
			String str) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(ARGS_ITEMS_DELIMITER);
			Map<Integer, Float[]> map = new HashMap<Integer, Float[]>(
					arr.length);
			for (String s : arr) {
				String[] subArr = s.split(DELIMITER_INNER_ITEM);
				Integer subKey = Integer.parseInt(subArr[0]);
				Float[] itemArray = new Float[subArr.length - 1];
				for (int i = 1; i < subArr.length; i++) {
					itemArray[i - 1] = Float.parseFloat(subArr[i]);
				}
				map.put(subKey, itemArray);
			}
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 把以1_2|1_3类似格式的字符串分解成HashMap<Integer,Integer>
	 * 
	 * @param str
	 * @return
	 */
	public static Map<Integer, Integer> delimiterString2IntMap(String str) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(ARGS_ITEMS_DELIMITER);
			Map<Integer, Integer> map = new HashMap<Integer, Integer>(
					arr.length);
			for (String s : arr) {
				String[] subArr = s.split(DELIMITER_INNER_ITEM);
				map.put(Integer.parseInt(subArr[0]), Integer
						.parseInt(subArr[1]));
			}
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 把以1_2|1_3类似格式的字符串分解成HashMap<Long,Long>,其中List为List<String>
	 * 
	 * @param str
	 * @return
	 */
	public static Map<Long, Long> delimiterString2Long2Map(String str) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(ARGS_ITEMS_DELIMITER);
			Map<Long, Long> map = new HashMap<Long, Long>(arr.length);
			for (String s : arr) {
				String[] subArr = s.split(DELIMITER_INNER_ITEM);
				map.put(Long.parseLong(subArr[0]), Long.parseLong(subArr[1]));
			}
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 把以1_2|1_3类似格式的字符串分解成HashMap<Long,Double>,其中List为List<String>
	 * 
	 * @param str
	 * @return
	 */
	public static Map<Long, Float> delimiterString2Long3Map(String str) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(ARGS_ITEMS_DELIMITER);
			Map<Long, Float> map = new HashMap<Long, Float>(arr.length);
			for (String s : arr) {
				String[] subArr = s.split(DELIMITER_INNER_ITEM);
				map.put(Long.parseLong(subArr[0]), Float.parseFloat(subArr[1]));
			}
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 把以1_2|1_3类似格式的字符串分解成Map<Integer, Float>,其中List为List<String>
	 * 
	 * @param str
	 * @return
	 */
	public static Map<Integer, Float[]> delimiterString2IntegerFloatMap(
			String str) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(ARGS_ITEMS_DELIMITER);
			Map<Integer, Float[]> map = new HashMap<Integer, Float[]>(
					arr.length);
			for (String s : arr) {
				String[] subArr = s.split(DELIMITER_INNER_ITEM);
				Float[] value = new Float[subArr.length - 1];
				for (int i = 1; i < subArr.length; i++) {
					value[i - 1] = Float.parseFloat(subArr[i]);
				}
				map.put(Integer.parseInt(subArr[0]), value);
			}
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 把HashMap<String,Object>分解成以1_a|1_b格式的字符串
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String delimiterMap2String(Map map) {
		StringBuilder bf = null;
		if (map != null && map.size() > 0) {
			for (Object key : map.keySet()) {
				Object obj = map.get(key);
				if (obj != null) {
					if (bf == null) {
						bf = new StringBuilder();
					} else {
						bf.append(DELIMITER_BETWEEN_ITEMS);
					}
					bf.append(key).append(DELIMITER_INNER_ITEM).append(
							map.get(key));
				}
			}
		}
		return bf == null ? null : bf.toString();
	}

	/**
	 * 把HashMap分解成以1_a|1_3_b类似格式的字符串
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String delimiterListMap2String(Map map) {
		StringBuilder bf = null;
		if (map != null && map.size() > 0) {
			for (Object key : map.keySet()) {
				Object obj = map.get(key);
				if (obj != null) {
					if (bf == null) {
						bf = new StringBuilder();
					} else {
						bf.append(DELIMITER_BETWEEN_ITEMS);
					}
					bf.append(key).append(DELIMITER_INNER_ITEM).append(
							map.get(key));
				}
			}
		}
		return bf == null ? null : bf.toString();
	}

	/**
	 * 把以1_3_4_6|类似格式的字符串分解成HashMap<String,List<Integer>><br>
	 * 
	 * @param str
	 * @return
	 */
	public static Map<String, List<Integer>> delimiterString2MapList(String str) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(ARGS_ITEMS_DELIMITER);
			Map<String, List<Integer>> map = new HashMap<String, List<Integer>>(
					arr.length);
			for (String s : arr) {
				String[] subArr = s.split(DELIMITER_INNER_ITEM);
				List<Integer> list = map.get(subArr[0]);
				if (list == null) {
					list = new ArrayList<Integer>();
					map.put(subArr[0], list);
				}
				for (int i = 1; i < subArr.length; i++) {
					list.add(Integer.parseInt(subArr[i]));
				}
			}
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 把以1_3_4_6|类似格式的字符串分解成HashMap<Integer,List<Integer>><br>
	 * 
	 * @param str
	 * @return
	 */
	public static Map<Integer, List<Integer>> delimiterString2IntgerMapList(
			String str) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(ARGS_ITEMS_DELIMITER);
			Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>(
					arr.length);
			for (String s : arr) {
				String[] subArr = s.split(DELIMITER_INNER_ITEM);
				List<Integer> list = map.get(subArr[0]);
				if (list == null) {
					list = new ArrayList<Integer>();
					map.put(Integer.parseInt(subArr[0]), list);
				}
				for (int i = 1; i < subArr.length; i++) {
					list.add(Integer.parseInt(subArr[i]));
				}
			}
			return map;
		} else {
			return null;
		}
	}

	/**
	 * @param map
	 * @return
	 */
	public static String delimiterMapList2String(Map<String, List<Integer>> map) {
		StringBuilder bf = null;
		if (map != null && map.size() > 0) {
			for (String key : map.keySet()) {
				List<Integer> list = map.get(key);
				if (!CollectionUtils.isEmpty(list)) {
					if (bf == null) {
						bf = new StringBuilder();
					} else {
						bf.append(DELIMITER_BETWEEN_ITEMS);
					}
					bf.append(key).append(DELIMITER_INNER_ITEM);
					for (int i = 0, size = list.size(); i < size; i++) {
						if (i == size - 1) {
							bf.append(list.get(i));
						} else {
							bf.append(list.get(i)).append(DELIMITER_INNER_ITEM);
						}
					}
				}
			}
			return bf == null ? null : bf.toString();
		} else {
			return null;
		}
	}

	/**
	 * 将字符串解释成Map<String,Long>
	 * 
	 * @param str
	 * @return Map<String,Long>
	 */
	public static Map<String, Long> delimiterString2Map(String str) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(ARGS_ITEMS_DELIMITER);
			Map<String, Long> map = new HashMap<String, Long>(arr.length);
			for (String s : arr) {
				String[] subArr = s.split(DELIMITER_INNER_ITEM);
				map.put(subArr[0], Long.parseLong(subArr[1]));
			}
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 将字符串解释成Map<String,Long>
	 * 
	 * @param str
	 * @return Map<String,Long>
	 */
	public static Map<String, Long> delimiterString2Map1(String str) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(ARGS_ITEMS_DELIMITER);
			Map<String, Long> map = new HashMap<String, Long>(arr.length);
			map.put(arr[0], Long.parseLong(arr[1]));
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 将格式如1_0.5|2_1的字符串解释成Map<Integer,Double>
	 * 
	 * @param str
	 * @return
	 */
	public static Map<Integer, Double> delimiterString2DoubleMap(String str) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(ARGS_ITEMS_DELIMITER);
			Map<Integer, Double> map = new HashMap<Integer, Double>(arr.length);
			for (String s : arr) {
				String[] subArr = s.split(DELIMITER_INNER_ITEM);
				map.put(Integer.parseInt(subArr[0]), Double
						.parseDouble(subArr[1]));
			}
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 将格式如1_2_3|2_1_3的字符串解释成Map<Integer,Map<Integer, Integer>>
	 * 
	 * @param str
	 * @return
	 */
	public static Map<Integer, Map<Integer, Integer>> delimiterString2Map2(
			String str) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(ARGS_ITEMS_DELIMITER);
			Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>(
					arr.length);
			Map<Integer, Integer> subMap = null;
			for (String s : arr) {
				String[] subArr = s.split(DELIMITER_INNER_ITEM);
				if (map.get(Integer.parseInt(subArr[0])) == null) {
					subMap = new HashMap<Integer, Integer>();
					map.put(Integer.parseInt(subArr[0]), subMap);
				} else {
					subMap = map.get(Integer.parseInt(subArr[0]));
				}
				subMap.put(Integer.parseInt(subArr[1]), Integer
						.parseInt(subArr[2]));
			}
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 将格式如1_2_3|2_1_3的字符串解释成Map<Integer,Map<Integer, Integer>>
	 * 
	 * @param str
	 * @return
	 */
	public static Map<Integer, Map<Integer, String>> delimiterString2Map3(
			Map<Integer, Map<Integer, String>> map, String str) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(ARGS_ITEMS_DELIMITER);
			Map<Integer, String> subMap = null;
			for (String s : arr) {
				String[] subArr = s.split(DELIMITER_INNER_ITEM);
				if (map.get(Integer.parseInt(subArr[0])) == null) {
					subMap = new HashMap<Integer, String>();
					map.put(Integer.parseInt(subArr[0]), subMap);
				} else {
					subMap = map.get(Integer.parseInt(subArr[0]));
				}
				subMap.put(Integer.parseInt(subArr[1]), subArr[2]);
			}
			return map;
		} else {
			return null;
		}
	}

	public static List<Integer[]> delimiterString2ListIntegerArray(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		String[] arr = str.split(ARGS_ITEMS_DELIMITER);
		if (arr == null || arr.length == 0) {
			return null;
		}
		List<Integer[]> result = new ArrayList<Integer[]>(arr.length);
		for (String temp : arr) {
			String[] tempArr = temp.split(DELIMITER_INNER_ITEM);
			Integer[] value = new Integer[tempArr.length];
			for (int i = 0; i < tempArr.length; i++) {
				value[i] = Integer.parseInt(tempArr[i]);
			}
			result.add(value);
		}
		return result;
	}

	/**
	 * 把以1_2_3|2_3_4|3_5_6_7类似格式的字符串分解成HashMap<Integer,List<Double[]>
	 * 
	 * @param str
	 * @return
	 */
	public static Map<Integer, List<Double[]>> delimiterString2IntegerListDoubleArrMap(
			String str) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(ARGS_ITEMS_DELIMITER);
			Map<Integer, List<Double[]>> map = new HashMap<Integer, List<Double[]>>(
					arr.length);
			for (String s : arr) {
				String[] subArr = s.split(DELIMITER_INNER_ITEM);
				Integer subKey = Integer.parseInt(subArr[0]);
				List<Double[]> list = map.get(subKey);
				if (list == null) {
					list = new ArrayList<Double[]>();
					map.put(subKey, list);
				}
				Double[] itemArray = new Double[subArr.length - 1];
				for (int i = 1; i < subArr.length; i++) {
					itemArray[i - 1] = Double.parseDouble(subArr[i]);
				}
				list.add(itemArray);
			}
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 把以1_2_3|2_3_4|3_5_6_7类似格式的字符串分解成HashMap<Integer,List<Double[]>
	 * 
	 * @param str
	 * @return
	 */
	public static String delimiterIntegerListDoubleArrMap2String(
			Map<Integer, List<Double[]>> map) {
		StringBuilder bf = null;
		if (map != null && map.size() > 0) {
			for (Integer key : map.keySet()) {
				List<Double[]> list = map.get(key);
				if (!CollectionUtils.isEmpty(list)) {
					for (int i = 0; i < list.size(); i++) {
						if (bf == null) {
							bf = new StringBuilder();
						} else {
							bf.append(DELIMITER_BETWEEN_ITEMS);
						}
						bf.append(key);
						for (Double value : list.get(i)) {
							bf.append(DELIMITER_INNER_ITEM).append(value);
						}
					}
				}
			}
			return bf == null ? null : bf.toString();
		} else {
			return null;
		}
	}

	/**
	 * 将1_2_3_4_5的格式转化为List<Integer>
	 * 
	 * @param str
	 * @return
	 */
	public static List<Integer> divideString2IntegerList(String str) {
		String[] arr = str.split(DELIMITER_INNER_ITEM);
		List<Integer> list = new ArrayList<Integer>(arr.length);
		for (String s : arr) {
			list.add(Integer.parseInt(s));
		}
		return list;
	}

	/**
	 * 将1_2_6｜2_1_5转第为Map<Integer,List<Integer>后面两个值是指一个范围<br>
	 * 如2_6是指{2,3,4,5,6}
	 * 
	 * @param str
	 * @return
	 */
	public static Map<Integer, List<Integer>> divideString2IntegerMap(String str) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(ARGS_ITEMS_DELIMITER);
			Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>(
					arr.length);
			for (String s : arr) {
				String[] subArr = s.split(DELIMITER_INNER_ITEM);
				Integer subKey = Integer.parseInt(subArr[0]);
				List<Integer> idList = new ArrayList<Integer>();
				for (int i = Integer.parseInt(subArr[1]); i <= Integer
						.parseInt(subArr[2]); i++) {
					idList.add(i);
				}
				map.put(subKey, idList);
			}
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 将List<Integer>的格式转化为1_2_3_4_5
	 * 
	 * @param list
	 * @return
	 */
	public static String divideIntegerList2String(List<Integer> list) {
		if (!CollectionUtils.isEmpty(list)) {
			StringBuffer sf = new StringBuffer();
			for (int i = 0; i < list.size(); i++) {
				if (i != 0) {
					sf.append(DELIMITER_INNER_ITEM);
				}
				sf.append(list.get(i));
			}
			return sf.toString();
		}
		return null;
	}

	/**
	 * 把以1_3_4_6|类似格式的字符串分解成HashMap<Integer,Object><br>
	 * 
	 * @param str
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<Integer, Object> delimiterString2IntgerMapObject(
			String str) {
		if (str != null && str.length() > 0) {
			String[] arr = str.split(ARGS_ITEMS_DELIMITER);
			Map<Integer, Object> map = new HashMap<Integer, Object>(arr.length);
			for (String s : arr) {
				String[] subArr = s.split(DELIMITER_INNER_ITEM);
				if (subArr.length == 2) {
					map.put(Integer.parseInt(subArr[0]), subArr[1]);
				} else if (subArr.length > 2) {
					List<Integer> list = (List<Integer>) map.get(subArr[0]);
					if (list == null) {
						list = new ArrayList<Integer>();
						map.put(Integer.parseInt(subArr[0]), list);
					}
					for (int i = 1; i < subArr.length; i++) {
						list.add(Integer.parseInt(subArr[i]));
					}
				}
			}
			return map;
		} else {
			return null;
		}
	}
	/**
	 * 把以delimiter分割的字符串分解成数组T[]
	 * 
	 * @param str
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] delimiterString2Array(String str, String delimiter, Class<T> c) {
		String tempDelimiter = DELIMITER_BETWEEN_ITEMS.equals(delimiter) ? DELIMITER_ARGS_ITEMS
				: delimiter;
		String[] arr = null;
		if (str != null && str.length() > 0) {
			arr = str.split(tempDelimiter);
		}
		if (arr == null || arr.length == 0) {
			return null;
		}

		T[] newArray = (T[]) Array.newInstance(c, arr.length);
		for (int i = 0; i < arr.length; i++) {
			newArray[i] = stringNumber2BasicTypes(arr[i], c);
		}
		return newArray;
	}
	/**
	 * 构造字符串,delimiter参数为分隔符，譬如delimiter为|分隔符，则格式为object|...|object
	 * 
	 * @param objects
	 * @return
	 */
	public static <T> String constructArray2String(String delimiter, T[] objs) {
		StringBuilder bf = null;
		if (objs != null && objs.length > 0) {
			for (T obj : objs) {
				if (obj != null) {
					if (bf == null) {
						bf = new StringBuilder();
					} else {
						bf.append(delimiter);
					}
					bf.append(obj);
				}
			}
		}
		return bf == null ? null : bf.toString();
	}
	/**
	 * 把以1_2|2_3分割的字符串分解成List<T[]>
	 * 
	 * @param str
	 * @return
	 */
	public static <T> List<T[]> delimiterString2ListArray(String str, Class<T> c) {
		if (str == null|| str.length() == 0) {
			return null;
		}
		String[] arr = str.split(DELIMITER_ARGS_ITEMS);
		if (arr == null || arr.length == 0) {
			return null;
		}
		List<T[]> result = new ArrayList<T[]>(arr.length);
		for (int i = 0; i < arr.length; i++) {
			result.add(delimiterString2Array(arr[i], DELIMITER_INNER_ITEM, c));
		}
		return result;
	}
	/**
	 * 构造字符串,把List<T[]>转为1_2|2_3存储
	 * 
	 * @param objects
	 * @return
	 */
	public static <T> String constructListArray2String(List<T[]> objs) {
		StringBuilder bf = null;
		if (objs != null && objs.size() > 0) {
			for (T[] array : objs) {
				if (array != null) {
					if (bf == null) {
						bf = new StringBuilder();
					} else {
						bf.append(DELIMITER_BETWEEN_ITEMS);
					}
					bf.append(constructArray2String(DELIMITER_INNER_ITEM,array));
				}
			}
		}
		return bf == null ? null : bf.toString();
	}
	public static String generalKey(String value){
		StringBuilder sb = new StringBuilder(value);
		if(value.length() > 0 && value.length() < 6){
			for (int i = value.length()-1; i < 6-value.length(); i++) {
				sb.append("0");
			}
		}else{
			sb.append("000000");
		}
		Random random = new Random();
		return sb.toString().toUpperCase()+""+DateUtil.getCurrentTime()/1000+""+random.nextInt(9)+""+random.nextInt(9)+""+random.nextInt(9)+""+random.nextInt(9);
	}
	public static void main(String[] args) {
		System.out.println("ROL00221321431335968".length());
		Date date = new Date();
		
		Random random = new Random();
		System.out.println(DateUtil.getCurrentTime()+""+random.nextInt(9)+""+random.nextInt(9)+""+random.nextInt(9)+""+random.nextInt(9));
	}
}
