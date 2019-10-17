<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page contentType="image/jpeg" import="javax.imageio.*" %>
<%@ page import="com.gdyiko.tool.Image"%>
<%
Image image =new Image();
response.reset();
//设置页面不缓存
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);


// 输出图象到页面
ImageIO.write(image.creatImage(), "JPEG", response.getOutputStream());
// 将认证码存入SESSION
String string =image.sRand;
//session.removeAttribute("numRandom");
session.setAttribute("numRandom",string);
//System.out.println("---验证码--"+session.getAttribute("numRandom"));
//清除
out.clear();
out = pageContext.pushBody();
%> 

