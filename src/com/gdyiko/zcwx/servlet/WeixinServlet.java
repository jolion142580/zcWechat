package com.gdyiko.zcwx.servlet;

import com.gdyiko.zcwx.service.CoreService;
import com.gdyiko.zcwx.weixinUtils.CheckUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class WeixinServlet
 */
public class WeixinServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    /**
     * 确认请求来自微信服务器
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            System.out.println("开始签名校验");

            // 微信加密签名
            String signature = request.getParameter("signature");
            // 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
            // 随机字符串
            String echostr = request.getParameter("echostr");
//        System.out.println("::::::::::::"+echostr);
            PrintWriter out = response.getWriter();
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            if (new CheckUtil().checkSignature(signature, timestamp, nonce)) {
                //        	System.out.println("===12:");
                out.print(echostr);
    /*            out.print(signature);
                out.print(timestamp);
                out.print(nonce);*/
            }
            out.close();
            out = null;
        } catch (IOException e) {
            System.out.println("【weixinServlet】get请求异常");
        } finally {
            System.out.println("【weixinServlet】get结束");

        }
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("11111111111");
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            String problems = request.getParameter("problems");
            // 调用核心业务类接收消息、处理消息
            String respMessage = CoreService.processRequest(request);
//       System.out.println("测试！！"+respMessage);
            // 响应消息
            PrintWriter out = response.getWriter();
            out.print(respMessage);
//        System.out.println("end2-----");
            out.close();
        } catch (IOException e) {
            System.out.println("【weixinServlet】post请求异常");
        } finally {
            System.out.println("【weixinServlet】post结束");

        }
    }

}
