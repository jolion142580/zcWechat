<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="image/jpeg" import="javax.imageio.*"%>
<%@ page import="com.gdyiko.tool.Image"%>
<%
	int codeLength = 6;
	String code = "";
	for (int i = 0; i < codeLength; i++) {
		code += (int) (Math.random() * 9);
	}
	System.out.println(code);

	session.setMaxInactiveInterval(10 * 60);
	session.removeAttribute("smscode");
	session.setAttribute("smscode", code);
%>

