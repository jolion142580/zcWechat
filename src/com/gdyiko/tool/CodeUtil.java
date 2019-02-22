package com.gdyiko.tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.hibernate.id.UUIDHexGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodeUtil {
	private static Logger log = LoggerFactory.getLogger(CodeUtil.class);

	public static final long MD5_KEY = System.currentTimeMillis();
	

	
	public static void serialGenerate(File file,File file2,int codeLen,String name,Integer num,Integer itemId,Integer type,String rewards,Integer isTypeRepeat) {
		try {
			UUIDHexGenerator uuidGenerator = new UUIDHexGenerator();
			WritableWorkbook book=Workbook.createWorkbook(file);// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet=book.createSheet("激活码",0);// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			Label label0_0=new Label(0,0,"类型");// 将定义好的单元格添加到工作表中
			Label label1_0=new Label(1,0,"名称");
			Label label4_0=new Label(4,0,"发放物品");
			Label label2_0=new Label(2,0,"道具id");
			Label label3_0=new Label(3,0,"激活码");
		
			
			sheet.addCell(label0_0);
			sheet.addCell(label1_0);
			sheet.addCell(label4_0);
			sheet.addCell(label2_0);
			sheet.addCell(label3_0);
		    file2.createNewFile();
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file2),"UTF-8");
			StringBuilder sb = new StringBuilder(" insert into identify_code(identifyCode,itemId,type,rewards,isTypeRepeat) values");
			for (int i = 0; i < num; i++) {		
				/*String se = StringUtils.getMD5(
						MD5_KEY + uuidGenerator.generate(null, null).toString()
								.substring(21)).substring(8, 20).toUpperCase();*/
				String se = null;
				if(codeLen > 0){
					se=GenerateUtil.getUUID(codeLen);
				}else {
					se=GenerateUtil.getUUID(20);
				}
				Label label0_x=new Label(0,(i+1),type.toString());
				Label label1_x=new Label(1,(i+1),name);
				Label label4_x=new Label(4,(i+1),rewards);
				Label label2_x=new Label(2,(i+1),itemId.toString());
				Label label3_x = new Label(3, (i + 1), se);
				sheet.addCell(label0_x);
				sheet.addCell(label1_x);
				sheet.addCell(label4_x);
				sheet.addCell(label2_x);
				sheet.addCell(label3_x);
				if(i < num-1){
				   sb.append("('"+se+"',"+itemId+","+type+",'"+rewards+"',"+isTypeRepeat+"),");
				}else{
				   sb.append("('"+se+"',"+itemId+","+type+",'"+rewards+"',"+isTypeRepeat+");");
				}
				
			}
			out.write(sb.toString());
			out.close();
			book.write();
			book.close();
		} catch (Exception e) {
			log.error("生成激活码出错！", e);
		}
	}
    
	public static void main(String[] args) {
		UUIDHexGenerator uuidGenerator = new UUIDHexGenerator();
		StringBuffer sf = new StringBuffer();
		Set<String> set = new HashSet<String>();
		for (int i = 0; i < 10000; i++) {
			String se = StringUtils.getMD5(
					MD5_KEY
							+ uuidGenerator.generate(null, null).toString()
									.substring(21)).substring(8, 20)
					.toUpperCase();
			if (set.contains(se)) {
				System.out.println("出错...");
			} else {
				set.add(se);
			}
			sf.append(se).append("\n");
		}
		try {
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(
							"d:\\code\\identityCode.txt"), "UTF-8"));
			bw.write(sf.toString());
			bw.close();
		} catch (Exception e) {
			log.error("生成激活码出错！", e);
		}
		// try {
		// BufferedReader br = new BufferedReader(new InputStreamReader(
		// new FileInputStream("d:\\code\\identityCode.txt"), "UTF-8"));
		// String se = null;
		// Set<String> set = new HashSet<String>();
		// while ((se = br.readLine()) != null) {
		// if (set.contains(se)) {
		// System.out.println(se);
		// System.out.println("出错...");
		// } else {
		// set.add(se);
		// }
		// }
		// } catch (Exception e) {
		// log.error("生成激活码出错！", e);
		//		}
	}

}
