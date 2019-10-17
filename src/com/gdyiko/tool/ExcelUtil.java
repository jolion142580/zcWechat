package com.gdyiko.tool;
import java.io.*;
import java.util.Date;
import java.util.List;

import com.game.commons.util.CollectionUtil;
import com.gdyiko.common.helper.AttrValue;


import jxl.*;
import jxl.write.*;
 

public class ExcelUtil {
	public static void main(String args[]){
		try{// 打开文件
			WritableWorkbook book=Workbook.createWorkbook(new File("serial-"+DateUtil.getYearDateStr(new Date())+".xls"));// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet=book.createSheet("第一页",0);// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为test
			Label label0_0=new Label(0,0,"类型");// 将定义好的单元格添加到工作表中
			Label label1_0=new Label(1,0,"激活码");
			sheet.addCell(label0_0);
			sheet.addCell(label1_0);
			/*
			* 生成一个保存数字的单元格 必须使用Number的完整包路径，否则有语法歧义
			* 单元格位置是第二列，第一行，值为789.123
			*/
			//jxl.write.Number number = new jxl.write.Number(1,0,789.123);
			//sheet.addCell(number);// 写入数据并关闭文件
			book.write();
			book.close();
		}catch(Exception e){System.out.println(e);
		}
	}
	
	public static <T> void generalXsl(OutputStream os, String name,
			List<AttrValue> titleList, List<T> datas) throws Exception {
		WritableWorkbook book = Workbook.createWorkbook(os);
		WritableSheet sheet = book.createSheet(name, 0);
		int i = 0;
		int j = 1;
		for (AttrValue attrValue : titleList) {
			Label labelTitle = new Label(i, 0, attrValue.getName());
			sheet.addCell(labelTitle);
			i++;
		}
		if(!CollectionUtil.isEmpty(datas)){
			for (T data : datas) {
				i = 0;
				for (AttrValue attrValue : titleList) {
					Label labelData = new Label(i, j, attrValue.convert(BeanUtil
							.getBeanProperty(data, attrValue.getColName())));
					sheet.addCell(labelData);
					i++;
				}
				j++;
			}
		}
		book.write();
		book.close();
	}
}

