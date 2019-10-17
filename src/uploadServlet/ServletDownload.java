package uploadServlet;

import java.io.IOException;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;

/**
 * ServletDownload
 */

public class ServletDownload extends HttpServlet {
 private ServletConfig config;
 final public void init(ServletConfig config) throws ServletException {
  this.config = config;
 }
 protected void doGet(HttpServletRequest request,
   HttpServletResponse response) throws ServletException, IOException {
  String temp_p =request.getParameter("downloadFileName");
  byte[] temp_t=temp_p.getBytes("ISO8859_1");
  String fileName=new String(temp_t,"GBK");
  SmartUpload mySmartUpload = new SmartUpload();
  try {
   mySmartUpload.initialize(config, request, response);
   mySmartUpload.setContentDisposition(null);
   mySmartUpload.downloadFile("/"+fileName);
   ///out.println("<script type='text/javascript' >window.close();</script>");
  } catch (Exception e) {
   e.printStackTrace();
  }
 }
 protected void doPost(HttpServletRequest request,
   HttpServletResponse response) throws ServletException, IOException {
  // TODO Auto-generated method stub
  doGet(request, response);
 }
}
