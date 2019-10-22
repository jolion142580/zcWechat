<%
String inputCode = request.getParameter("code");
String code = (String)session.getAttribute("numRandom");
if ( inputCode.equals(code) ){
%>true<%}else{%>false<%}%>
