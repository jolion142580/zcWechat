package com.gdyiko.tool;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.struts2.ServletActionContext;

/**
 * 
 * @author
 * @version
 */
public class ExportExcel {

	/**
	 * 生成excel 单表
	 * 
	 * @param headers
	 *            输出表头
	 * @param databaseFieldNames
	 *            数据库对应表头的数组
	 * @param needNo
	 *            是否生成序号 若为true 则输出表头必须多一列用于显示序号
	 * @param tableName
	 *            表名
	 * @param outFullPath
	 *            输出的文件路径
	 * @param 具体参考重构方法
	 */
	public void exportExcel(String[] headers, String[] databaseFieldNames,
			boolean needNo, String tableName, String outFullPath) {
		// 表头数组
		// String[] headers = { "序号", "被抽样单位（人）", "样品名称", "样品编号",
		// "抽样数量","抽样基数","标称来源","检验项目","检测结果" };
		// 数据库字段数组 注意：必须等于表头数组的个数，序号列除外。
		// String[] databaseFieldNames
		// ={"examinedUnitName","sampleName","sampleNumber","samplingNumber","samplingBaseNumber","nominalSource","detectioInspections","detectioVerdict"};
		// 是否需要生成 序号 注意：这里如果选择生成序号，那么表头数组必须存在显示序号的列。
		// boolean needNo =true;
		// 查询的表名
		// String tableName="ss_foodSampling_info";
		// 生成sql语句
		String sql = buildSql(tableName, databaseFieldNames, needNo, "");
		System.out.println(sql);
		try {
			int length = databaseFieldNames.length;
			if (needNo) {
				length = databaseFieldNames.length + 1;
			}
			List<String[]> list = buildDate(sql, length);
			// String fileName =PrimaryProduce.produce();
			// String serverUrl=
			// ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
			// OutputStream out = new
			// FileOutputStream(serverUrl+"/upload/"+fileName+".xls");
			OutputStream out = new FileOutputStream(outFullPath);
			exportExcel("sheet", headers, list, out, "");
			out.close();
			System.out.println("excel导出成功！");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成excel 联表查询
	 * 
	 * @param headers
	 *            输出表头
	 * @param databaseFieldNames
	 *            数据库对应表头的数组
	 * @param sql语句
	 * @param outFullPath
	 *            输出的文件路径
	 * @param 具体参考重构方法
	 */
	public void exportExcel(String[] headers, String[] databaseFieldNames,
			String sql, String outFullPath) {
		// 表头数组
		// String[] headers = { "序号", "被抽样单位（人）", "样品名称", "样品编号",
		// "抽样数量","抽样基数","标称来源","检验项目","检测结果" };
		// 数据库字段数组 注意：必须等于表头数组的个数，序号列除外。
		// String[] databaseFieldNames
		// ={"examinedUnitName","sampleName","sampleNumber","samplingNumber","samplingBaseNumber","nominalSource","detectioInspections","detectioVerdict"};
		try {
			List<String[]> list = buildDate(sql, databaseFieldNames.length);
			// String fileName =PrimaryProduce.produce();
			// String serverUrl=
			// ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
			// OutputStream out = new
			// FileOutputStream(serverUrl+"/upload/"+fileName+".xls");
			OutputStream out = new FileOutputStream(outFullPath);
			exportExcel("sheet", headers, list, out, "");
			out.close();
			System.out.println("excel导出成功！");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 * 
	 * @param title
	 *            表格标题名
	 * @param headers
	 *            表格属性列名数组
	 * @param dataset
	 *            需要显示的数据集合,
	 * @param out
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param pattern
	 *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
	 */
	@SuppressWarnings("unchecked")
	public void exportExcel(String title, String[] headers,
			List<String[]> dataset, OutputStream out, String pattern) {
		title = "检测信息";
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 12);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		// font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);

		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		if (headers.length > 1) {
			for (short i = 0; i < headers.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style);
				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cell.setCellValue(text);
			}
		}
		// 遍历集合数据，产生数据行
		// 遍历集合数据，产生数据行
		Iterator<String[]> it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			String[] t = (String[]) it.next();
			for (short i = 0; i < t.length; i++) {
				HSSFCell cell = row.createCell(i);
				try {

					String value = t[i];
					// 判断值的类型后进行强制类型转换
					String textValue = null;
					textValue = value.toString();

					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
					if (textValue != null) {
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = p.matcher(textValue);
						if (matcher.matches()) {
							// 是数字当作double处理
							cell.setCellValue(Double.parseDouble(textValue));
						} else {
							HSSFRichTextString richString = new HSSFRichTextString(
									textValue);
							HSSFFont font3 = workbook.createFont();
							// font3.setColor(HSSFColor.BLUE.index);
							richString.applyFont(font3);
							cell.setCellValue(richString);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					// 清理资源
				}
			}
		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 * 
	 * @param title
	 *            表格标题名
	 * @param headers
	 *            表格属性列名数组
	 * @param dataset
	 *            需要显示的数据集合,
	 * @param out
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param pattern
	 *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void exportExcel(String title, String[] headers,
			List<String[]> dataset, List<String[]> dataset1,
			List<String[]> dataset2, List<String[]> dataset3,
			List<String[]> dataset4, OutputStream out, String pattern)
			throws IOException {
		title = "检测信息";
		// 声明一个工作薄
		System.out.println("路径--->"
				+ ServletActionContext.getRequest().getContextPath());
		File excel = new File("E:/gif/三水抽检系统/sscjxt/web/excel/baobiao.xls");
		FileInputStream is = new FileInputStream(excel);
		HSSFWorkbook workbook = new HSSFWorkbook(is);
		// 生成一个表格

		HSSFSheet sheet = workbook.getSheetAt(1);
		HSSFSheet sheet1 = workbook.getSheetAt(2);
		HSSFSheet sheet2 = workbook.getSheetAt(3);
		HSSFSheet sheet3 = workbook.getSheetAt(4);
		HSSFSheet sheet4 = workbook.getSheetAt(5);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 12);
		sheet1.setDefaultColumnWidth((short) 12);
		sheet2.setDefaultColumnWidth((short) 12);
		sheet3.setDefaultColumnWidth((short) 12);
		sheet4.setDefaultColumnWidth((short) 12);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		// font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);

		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		if (headers.length > 1) {
			for (short i = 0; i < headers.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style);
				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cell.setCellValue(text);
			}
		}
		// 遍历集合数据，产生数据行
		// 遍历集合数据，产生数据行
		Iterator<String[]> it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			String[] t = (String[]) it.next();
			for (short i = 0; i < t.length; i++) {
				HSSFCell cell = row.createCell(i);
				try {

					String value = t[i];
					// 判断值的类型后进行强制类型转换
					String textValue = null;
					textValue = value.toString();

					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
					if (textValue != null) {
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = p.matcher(textValue);
						if (matcher.matches()) {
							// 是数字当作double处理
							cell.setCellValue(Double.parseDouble(textValue));
						} else {
							HSSFRichTextString richString = new HSSFRichTextString(
									textValue);
							HSSFFont font3 = workbook.createFont();
							// font3.setColor(HSSFColor.BLUE.index);
							richString.applyFont(font3);
							cell.setCellValue(richString);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					// 清理资源
				}
			}
		}
		// sheet1
		// 产生表格标题行
		HSSFRow row1 = sheet1.createRow(0);
		if (headers.length > 1) {
			for (short i = 0; i < headers.length; i++) {
				HSSFCell cell = row1.createCell(i);
				cell.setCellStyle(style);
				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cell.setCellValue(text);
			}
		}
		// 遍历集合数据，产生数据行
		// 遍历集合数据，产生数据行
		Iterator<String[]> it1 = dataset1.iterator();
		int index1 = 0;
		while (it1.hasNext()) {
			index1++;
			row1 = sheet1.createRow(index1);
			String[] t = (String[]) it1.next();
			for (short i = 0; i < t.length; i++) {
				HSSFCell cell = row1.createCell(i);
				try {

					String value = t[i];
					// 判断值的类型后进行强制类型转换
					String textValue = null;
					textValue = value.toString();

					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
					if (textValue != null) {
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = p.matcher(textValue);
						if (matcher.matches()) {
							// 是数字当作double处理
							cell.setCellValue(Double.parseDouble(textValue));
						} else {
							HSSFRichTextString richString = new HSSFRichTextString(
									textValue);
							HSSFFont font3 = workbook.createFont();
							// font3.setColor(HSSFColor.BLUE.index);
							richString.applyFont(font3);
							cell.setCellValue(richString);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					// 清理资源
				}
			}
		}
		// sheet2
		// 产生表格标题行
		HSSFRow row2 = sheet2.createRow(0);
		if (headers.length > 1) {
			for (short i = 0; i < headers.length; i++) {
				HSSFCell cell = row2.createCell(i);
				cell.setCellStyle(style);
				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cell.setCellValue(text);
			}
		}
		// 遍历集合数据，产生数据行
		// 遍历集合数据，产生数据行
		Iterator<String[]> it2 = dataset2.iterator();
		int index2 = 0;
		while (it2.hasNext()) {
			index2++;
			row2 = sheet2.createRow(index2);
			String[] t = (String[]) it2.next();
			for (short i = 0; i < t.length; i++) {
				HSSFCell cell = row2.createCell(i);
				try {

					String value = t[i];
					// 判断值的类型后进行强制类型转换
					String textValue = null;
					textValue = value.toString();

					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
					if (textValue != null) {
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = p.matcher(textValue);
						if (matcher.matches()) {
							// 是数字当作double处理
							cell.setCellValue(Double.parseDouble(textValue));
						} else {
							HSSFRichTextString richString = new HSSFRichTextString(
									textValue);
							HSSFFont font3 = workbook.createFont();
							// font3.setColor(HSSFColor.BLUE.index);
							richString.applyFont(font3);
							cell.setCellValue(richString);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					// 清理资源
				}
			}
		}
		// sheet3
		// 产生表格标题行
		HSSFRow row3 = sheet3.createRow(0);
		if (headers.length > 1) {
			for (short i = 0; i < headers.length; i++) {
				HSSFCell cell = row3.createCell(i);
				cell.setCellStyle(style);
				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cell.setCellValue(text);
			}
		}
		// 遍历集合数据，产生数据行
		// 遍历集合数据，产生数据行
		Iterator<String[]> it3 = dataset3.iterator();
		int index3 = 0;
		while (it3.hasNext()) {
			index3++;
			row3 = sheet3.createRow(index3);
			String[] t = (String[]) it3.next();
			for (short i = 0; i < t.length; i++) {
				HSSFCell cell = row3.createCell(i);
				try {

					String value = t[i];
					// 判断值的类型后进行强制类型转换
					String textValue = null;
					textValue = value.toString();

					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
					if (textValue != null) {
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = p.matcher(textValue);
						if (matcher.matches()) {
							// 是数字当作double处理
							cell.setCellValue(Double.parseDouble(textValue));
						} else {
							HSSFRichTextString richString = new HSSFRichTextString(
									textValue);
							HSSFFont font3 = workbook.createFont();
							// font3.setColor(HSSFColor.BLUE.index);
							richString.applyFont(font3);
							cell.setCellValue(richString);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					// 清理资源
				}
			}
		}
		// sheet4
		// 产生表格标题行
		HSSFRow row4 = sheet4.createRow(0);
		if (headers.length > 1) {
			for (short i = 0; i < headers.length; i++) {
				HSSFCell cell = row4.createCell(i);
				cell.setCellStyle(style);
				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cell.setCellValue(text);
			}
		}
		// 遍历集合数据，产生数据行
		// 遍历集合数据，产生数据行
		Iterator<String[]> it4 = dataset4.iterator();
		int index4 = 0;
		while (it4.hasNext()) {
			index4++;
			row4 = sheet4.createRow(index4);
			String[] t = (String[]) it4.next();
			for (short i = 0; i < t.length; i++) {
				HSSFCell cell = row4.createCell(i);
				try {

					String value = t[i];
					// 判断值的类型后进行强制类型转换
					String textValue = null;
					textValue = value.toString();

					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
					if (textValue != null) {
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = p.matcher(textValue);
						if (matcher.matches()) {
							// 是数字当作double处理
							cell.setCellValue(Double.parseDouble(textValue));
						} else {
							HSSFRichTextString richString = new HSSFRichTextString(
									textValue);
							HSSFFont font3 = workbook.createFont();
							// font3.setColor(HSSFColor.BLUE.index);
							richString.applyFont(font3);
							cell.setCellValue(richString);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					// 清理资源
				}
			}
		}
		HSSFSheet sheet0 = workbook.getSheetAt(0);
		sheet0.setForceFormulaRecalculation(true);
		int rowcount = sheet0.getLastRowNum();
		for (int k = 0; k < rowcount; k++) {
			updateFormula(workbook, sheet0, k);
		}
		System.out.println(rowcount);
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		/*
		 * //表头数组 String[] headers = { "序号", "被抽样单位（人）", "样品名称", "样品编号",
		 * "抽样数量","抽样基数","标称来源","检验项目","检测结果" }; //数据库字段数组 注意：必须等于表头数组的个数，序号列除外。
		 * String[] databaseFieldNames
		 * ={"examinedUnitName","sampleName","sampleNumber"
		 * ,"samplingNumber","samplingBaseNumber"
		 * ,"nominalSource","detectioInspections","detectioVerdict"}; //是否需要生成
		 * 序号 注意：这里如果选择生成序号，那么表头数组必须存在显示序号的列。 boolean needNo =true; //查询的表名
		 * String tableName="ss_foodSampling_info"; //生成sql语句 String sql
		 * =buildSql(tableName,databaseFieldNames,needNo);
		 * System.out.println(sql); try { int length =databaseFieldNames.length;
		 * if(needNo){ length =databaseFieldNames.length+1; } List<String[]>
		 * list= buildDate(sql,length);
		 * 
		 * String fileName =PrimaryProduce.produce(); //String serverUrl=
		 * ServletActionContext
		 * .getRequest().getSession().getServletContext().getRealPath("/");
		 * //OutputStream out = new
		 * FileOutputStream(serverUrl+"/upload/"+fileName+".xls"); OutputStream
		 * out = new FileOutputStream("D:/"+fileName+".xls");
		 * exportExcel("",headers, list, out,""); out.close();
		 * System.out.println("excel导出成功！");
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */

	}

	// 生成查询sql
	public String buildSql(String tableName, String[] databaseFieldNames,
			boolean needNo, String whereString) {

		String filedssql = "select ";
		if (needNo) {
			filedssql += " ROW_NUMBER()over(order by creattime) as no, ";
		}
		for (int i = 0; i < databaseFieldNames.length; i++) {
			filedssql += databaseFieldNames[i];
			if (i < databaseFieldNames.length - 1) {
				filedssql += ",";
			}
		}
		filedssql = filedssql + " from " + tableName + " " + whereString;
		return filedssql;
	}

	// 生成数据
	public List<String[]> buildDate(String sql, int length) throws SQLException {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		DbHelp dbHelp = new DbHelp();
		Connection conn = dbHelp.getConnection("/db.properties");
		List<String[]> flowList = new ArrayList();
		pstm = conn.prepareStatement(sql);

		rs = pstm.executeQuery();
		if (rs != null) {

			while (rs.next()) {
				String[] object = new String[length];
				// object[0] =(rs.getString(1));
				for (int i = 0; i < length; i++) {
					object[i] = (rs.getString(i + 1));
				}
				flowList.add(object);
			}
			rs.close();

		}

		return flowList;
	}

	public void download(String path, HttpServletResponse response) {
		try {
			// path是指欲下载的文件的路径。
			File file = new File(path);
			// file.mkdirs();
			// 取得文件名。
			String filename = file.getName();
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/vnd.ms-excel;charset=gb2312");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private static void updateFormula(HSSFWorkbook wb, Sheet s, int row) {
		Row r = s.getRow(row);
		Cell c = null;
		HSSFFormulaEvaluator eval = null;
		if (wb instanceof HSSFWorkbook)
			eval = new HSSFFormulaEvaluator((HSSFWorkbook) wb);
		else if (wb instanceof HSSFWorkbook)
			eval = new HSSFFormulaEvaluator((HSSFWorkbook) wb);
		for (int i = r.getFirstCellNum(); i < r.getLastCellNum(); i++) {
			c = r.getCell(i);
			if (c.getCellType() == Cell.CELL_TYPE_FORMULA)
				eval.evaluateFormulaCell(c);
		}
	}
}
