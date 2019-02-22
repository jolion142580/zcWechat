import java.util.List;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gdyiko.zcwx.service.FAQService;

public class WordSegmentTest {
	public static void main(String[] args) throws JSONException {
		String str = "办理港澳通行证";

		/*
		 * Result term = ToAnalysis.parse(str); for (int i = 0; i < term.size();
		 * i++) { String words = term.get(i).getName();// 获取单词 // String nominal
		 * = term.get(i).getNatureStr();// 获取词性 System.out.print(words + "\n" );
		 * String st = geta(words); System.out.println("---"+st); }
		 */

		String an = geta(str);
		// System.out.println("--11--"+an);
		JSONArray json = new JSONArray(an);
		JSONObject jsonobj = json.getJSONObject(0);
		String respContent = jsonobj.get("answer").toString();
		System.out.println("answer-----" + respContent);
	}

	public static String geta(String content) throws JSONException {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:applicationContext.xml");
		FAQService faqservice = (FAQService) context.getBean("FAQService");
		String respContent = null;
		Result term = ToAnalysis.parse(content);
		for (int i = 0; i < term.size(); i++) {
			String words = term.get(i).getName();// 获取单词
			// String nominal = term.get(i).getNatureStr();// 获取词性
			String res = faqservice.findAnswer(words);

			if (!"[]".equals(res)) {

				respContent = res;
			}

		}
		return respContent;
	}
}