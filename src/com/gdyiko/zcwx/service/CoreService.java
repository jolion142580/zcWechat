package com.gdyiko.zcwx.service;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.gdyiko.base.service.PropertieService;
import com.gdyiko.base.service.WeChatPropertieService;
import com.gdyiko.tool.PrimaryProduce;
import com.gdyiko.zcwx.po.WxTemplateLog;

import org.ansj.domain.Result;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gdyiko.zcwx.po.resp.*;
import com.gdyiko.zcwx.weixinUtils.MessageUtil;

public class CoreService {

    @Resource(name = "FAQService")
    FAQService faqService;

    @Autowired
    PropertieService propertieService;


   /**
     * 处理微信发来的请求
     *
     * @param request
     * @return
     */
    public static String processRequest(HttpServletRequest request) {
        String respMessage = null;
        //String path = request.getSession().getServletContext().getRealPath("/");
       // String path = "http://zcxzfwzx.chancheng.gov.cn/zcWechat/";
        String path = WeChatPropertieService.getPropertieByStatic("WeChatDNSURL");
        //String path = "http://192.168.1.108/weixin/";
        //System.out.println(path+"images/sbt_1a.png");
        try {
            // 默认返回的文本消息内容  
            String respContent = "请求处理异常，请稍候尝试！";
//            System.out.println("请求信息："+request);
            // xml请求解析  
            Map<String, String> requestMap = MessageUtil.parseXml(request);

            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
//            System.out.println("open_id:::"+fromUserName);
            // 公众帐号  
            String toUserName = requestMap.get("ToUserName");
//            System.out.println("toUserName:::"+toUserName);
            // 消息类型
            String msgType = requestMap.get("MsgType");
//            System.out.println("msgType:::"+msgType);

            // 回复文本消息  
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);

            // 回复图文消息  
            NewsMessage newsMessage = new NewsMessage();
            newsMessage.setToUserName(fromUserName);
            newsMessage.setFromUserName(toUserName);
            newsMessage.setCreateTime(new Date().getTime());
            newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
            newsMessage.setFuncFlag(0);

            // 文本消息
            //
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                //respContent = "您发送的是文本消息！";
                // 接收用户发送的文本消息内容
                String content = requestMap.get("Content");
//                System.out.println("content::::::::"+content);


                List<Article> articleList = new ArrayList<Article>();

                if ("？".equals(content)) {
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("您好，如需查询办事指南，请点击\"张槎街道行政服务中心\"微信服务号主页左下方“政务指南”，点击选择对应部门及事项或直接搜索关键字查询办事指南。回复以下关键字（电话、免费产检、生育津贴、产假、门诊、报销、社保、退休、户籍、失业、就业、招聘、居住证、入学、新生儿参保、零星报销）可查看具体内容。感谢您对张槎街道行政服务中心的关注。\n");

                    respContent = buffer.toString();
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                } else {
//                	String contents=URLEncoder.encode(content,"utf-8");;

                	/*ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
                    FAQService faqservice=(FAQService)context.getBean("FAQService");
                	
                	String res =faqservice.findAnswer(content);
            		 JSONArray json = new JSONArray(res);

            		 JSONObject jsonobj = json.getJSONObject(0);

            		 respContent=jsonobj.get("answer").toString();*/

                    String an = geta(content);

                    try {
                        JSONArray json = new JSONArray(an);
                        JSONObject jsonobj = json.getJSONObject(0);
                        respContent = jsonobj.get("answer").toString();
                    } catch (Exception e) {
                        respContent = "您好，如需查询办事指南，请点击“张槎街道行政服务中心”微信服务号主页左下方“政务指南”，点击选择对应部门及事项或直接搜索关键字查询办事指南。回复以下关键字（电话、免费产检、生育津贴、产假、门诊、报销、社保、退休、户籍、失业、就业、招聘、居住证、入学、新生儿参保、零星报销）可查看具体内容。感谢您对张槎街道行政服务中心的关注。\n";
                    }

                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }
            }
            // 图片消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "您发送的是图片消息！";
            }
            // 地理位置消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
            }
            // 链接消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "您发送的是链接消息！";
            }
            // 音频消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是音频消息！";
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
            }
            // 事件推送  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {

                System.out.println("事件推送！");
                // 事件类型  
                String eventType = requestMap.get("Event");
                // 订阅  
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("您好，欢迎关注\"张槎街道行政服务中心\"微信平台公众号。在这里可以查询办事指南、办件进度等等。");

                    respContent = buffer.toString();
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
//                	List<Article> articleList = new ArrayList<Article>();  
//                	Article article1 = new Article();  
//                    article1.setTitle("广东南邮英科信息科技有限公司欢迎您");  
//                    article1.setDescription("广东南邮英科信息科技有限公司成立于2006年，我公司主要从事信息化软件、物理网、系统集成等研发及服务，为客户提供专业的软硬件解决方案及技术服务。");  
//                    article1.setPicUrl(path+"images/gsjj_banner.png");  
//                    article1.setUrl(path+"index.htm");  

//                    articleList.add(article1);  
//                    // 设置图文消息个数  
//                    newsMessage.setArticleCount(articleList.size());  
//                    // 设置图文消息包含的图文集合  
//                    newsMessage.setArticles(articleList);  
//                    // 将图文消息对象转换成xml字符串  
//                    respMessage = MessageUtil.newsMessageToXml(newsMessage); 
                }
                // 自定义菜单点击事件  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    System.out.println("菜单点击！");
                    List<Article> articleList = new ArrayList<Article>();
                    Article article = new Article();
                    Article article1 = new Article();
                    Article article2 = new Article();
                    Article article3 = new Article();
                    // 事件KEY值，与创建自定义菜单时指定的KEY值对应
                    String eventKey = requestMap.get("EventKey");
                    if (eventKey.equals("bianminfuwu")) {
                        System.out.println("便民服务");
                        article.setTitle("便民服务");
                        article.setDescription("");
                        article.setPicUrl(path + "images/new_bianminfuwu.png");
                        // article.setUrl("http://blog.csdn.net/lyq8479/article/details/8937622");


                        article1.setTitle("最新资讯");
                        article1.setDescription("");
                        article1.setPicUrl(path + "images/weixin/zxzx.png");
                       // article1.setUrl("http://zcxzfwzx.chancheng.gov.cn/zcWechat/wxfb/zdsj/index.htm");
                        article1.setUrl(path+"wxfb/zdsj/index.htm");

                        article2.setTitle("实时公交查询(引用车来了app)");
                        article2.setDescription("");
                        article2.setPicUrl(path + "images/weixin/gj.png");
                        article2.setUrl("http://web.chelaile.net.cn/wwd/index?src=webapp_foshantraffic#");

                        article3.setTitle("港澳通行证续签(引用佛山公安微信公众平台)");
                        article3.setDescription("");
                        article3.setPicUrl(path + "images/weixin/txz.png");
                        article3.setUrl("http://wx.fsga.gov.cn/fsweixin/FsIndexZAServlet?service=18A761AB75264F79ACEC116290651D9F&pageIndex=1&pageSize=10&key=aad1d82b-f2d6-4658-89cb-8eaf6bc0a4bb");
                      
                      /*  Article article4 = new Article();
                        article4.setTitle("社保查询（引用佛山社保公众平台）");
                        article4.setDescription("");  
                        article4.setPicUrl(path+"images/weixin/wz.png");
                        article4.setUrl("http://sb.top2top.cn/wxcheck.aspx?oid=ooiNowCQBFSix-uXk3I79_JpvIbc&act=check");*/

                        Article article5 = new Article();
                        article5.setTitle("路况快照(引用佛山公安微信公众平台)");
                        article5.setDescription("");
                        article5.setPicUrl(path + "images/weixin/lk.png");
                        article5.setUrl("http://wx.fsga.gov.cn/fsweixin/QueryRoadInfoServlet2?oder=imgtitle&q_code=QS&key=f5fe29ba-c84e-448e-a4f9-b0e0da9f9207");
                        /*Article article6 = new Article();
                        article6.setTitle("法律咨询");  
                        article6.setDescription("");  
                        article6.setPicUrl(path+"images/falv.jpg");
                        article6.setUrl("http://blog.csdn.net/lyq8479/article/details/8941577");
                        
                        Article article7 = new Article();
                        article7.setTitle("出入境业务");  
                        article7.setDescription("");  
                        article7.setPicUrl(path+"images/weixin/aj.png");
                        article7.setUrl("http://125.95.19.22/fsweixin/FsIndexZAServlet?service=18A761AB75264F79ACEC116290651D9F&pageIndex=1&pageSize=10&key=e23f4b6b-0168-4e16-9456-8a04fe3265b5");
                        */
                        Article article8 = new Article();
                        article8.setTitle("交管业务(引用佛山公安微信公众平台)");
                        article8.setDescription("");
                        article8.setPicUrl(path + "images/weixin/jg.png");
                        article8.setUrl("http://wx.fsga.gov.cn/fsweixin/fsweb2/fsga/wscgs.jsp");
                        Article article9 = new Article();
                        article9.setTitle("户政业务(引用佛山公安微信公众平台)");
                        article9.setDescription("");
                        article9.setPicUrl(path + "images/weixin/hz.png");
                        article9.setUrl("http://wx.fsga.gov.cn/fsweixin/FsIndexZAServlet?service=A92D95C287AF4ECFAE20FEFCD9201980&pageIndex=1&pageSize=10&key=1d83931b-0d84-4019-abd5-c219ebe3839d");

                        articleList.add(article);
                        articleList.add(article1);
                        articleList.add(article2);
                        articleList.add(article3);
//                        articleList.add(article4);
                        articleList.add(article5); 
                        /*articleList.add(article6); 
                        articleList.add(article7); */
                        articleList.add(article8);
                        articleList.add(article9);


//                        System.out.println("返回信息："+respMessage);
                    }

                    if (eventKey.equals("online")) {
                        System.out.println("【网上办事网上办事】");
                        article.setTitle("网上办事");
                        article.setDescription("");
                        article.setPicUrl(path + "images/weixin/wsbs.png");

                        article1.setTitle("我要办事");
                        article1.setDescription("");
                        article1.setPicUrl(path + "images/weixin/wybs.png");
//                       article1.setUrl("http://zcxzfwzx.chancheng.gov.cn/zcWechat/ssAffairsInfo!findAffairByIsonline");

 //                       article1.setUrl("http://zcxzfwzx.chancheng.gov.cn/zcWechat/onlineApply!getOnlineDepart");
                        article1.setUrl(path+"onlineApply!getOnlineDepart");

                        article2.setTitle("办事记录");
                        article2.setDescription("");
                        article2.setPicUrl(path + "images/weixin/bsjl.png");
                       // article2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WeChatPropertieService.getPropertieByStatic("APPID")+"&redirect_uri=http://zcxzfwzx.chancheng.gov.cn/zcWechat/onlineApply!onlineApplyHistory&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
                        article2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WeChatPropertieService.getPropertieByStatic("APPID")+"&redirect_uri="+path+"onlineApply!onlineApplyHistory&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");

                        articleList.add(article);
                        articleList.add(article1);
                        articleList.add(article2);

                    }

                    if (eventKey.equals("onlinebooking")) {

                        article.setTitle("网上预约");
                        article.setDescription("");
                        article.setPicUrl(path + "images/weixin/wangshangyuyue.png");

                        article1.setTitle("我要预约");
                        article1.setDescription("");
                        article1.setPicUrl(path + "images/weixin/woyaoyuyue2.png");
                      //  article1.setUrl("http://zcxzfwzx.chancheng.gov.cn/zcWechat/newyuyueAgree.jsp");
                        article1.setUrl(path+"newyuyueAgree.jsp");

                        article2.setTitle("预约记录");
                        article2.setDescription("");
                        article2.setPicUrl(path + "images/weixin/yuyuejilu2.png");
                        //article2.setUrl("http://zcxzfwzx.chancheng.gov.cn/zcWechat/newwdyy_2.jsp");
//                        article2.setUrl(path+"newwdyy_2.jsp");
                        article2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WeChatPropertieService.getPropertieByStatic("APPID")+"&redirect_uri="+path+"newwdyy_2.jsp&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");

                        articleList.add(article);
                        articleList.add(article1);
                        articleList.add(article2);

                    }


                    newsMessage.setArticleCount(articleList.size());
                    newsMessage.setArticles(articleList);
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);

                }

                if (eventType.equals("TEMPLATESENDJOBFINISH")) {
                    String createtime = requestMap.get("CreateTime");
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    long lt = Long.parseLong(String.valueOf(createtime));
                    Date date = new Date(lt * 1000);

                    String MsgID = requestMap.get("MsgID");
                    String Status = requestMap.get("Status");
                    WxTemplateLog wxTemplateLog = new WxTemplateLog();
                    wxTemplateLog.setId(PrimaryProduce.produce());
                    wxTemplateLog.setOpenid(fromUserName);
                    wxTemplateLog.setCreattime(simpleDateFormat.format(date));
                    wxTemplateLog.setMsgid(MsgID);
                    wxTemplateLog.setStatus(Status);

                    System.out.println("--模版消息发送成功-=" + toUserName + "----" + fromUserName + "---" + createtime + "---" + MsgID + "---" + Status);
                    ApplicationContext context = new ClassPathXmlApplicationContext(
                            "classpath:applicationContext.xml");
                    WxTemplateLogService wxTemplateLogService = (WxTemplateLogService) context.getBean("wxTemplateLogService");
                    wxTemplateLogService.save(wxTemplateLog);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println("respMessage:::"+respMessage);
        return respMessage;
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
                return respContent;
            } else {
                respContent = "您好，如需查询办事指南，请点击“张槎街道行政服务中心”微信服务号主页左下方“政务指南”，点击选择对应部门及事项或直接搜索关键字查询办事指南。回复以下关键字（电话、免费产检、生育津贴、产假、门诊、报销、社保、退休、户籍、失业、就业、招聘、居住证、入学、新生儿参保、零星报销）可查看具体内容。感谢您对张槎街道行政服务中心的关注。\n";
            }

        }
        return respContent;
    }


}
