package uploadServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;
import com.jspsmart.upload.SmartUpload;
import java.io.File;
import java.util.Enumeration;
/**
 * ServletUpload
 */
public class ServletUpload extends HttpServlet {
 private ServletConfig config;

 final public void init(ServletConfig config) throws ServletException {
  this.config = config;
 }
 protected void doGet(HttpServletRequest request,
   HttpServletResponse response) throws ServletException, IOException {
  // TODO Auto-generated method stub
 
  PrintWriter out = response.getWriter();
  String propertiesName = "dbconfig.properties";
  long upDocName =0;
  String ext =""; 
  Properties p = new Properties();
  p.load(ServletUpload.class.getResourceAsStream(propertiesName));
  System.out.println(p.getProperty("path"));
  String realPath=p.getProperty("path");
  out.println("<HTML>");
  out.println("<BODY BGCOLOR='white'>");//
  out.println("<d:View>");
  out.println("");
  out.println("");
  String uploadlawid = request.getParameter("uploadlawid");
  java.lang.System.out.println("uploadlawid----->"+uploadlawid);


    File saveDir  = new File(realPath+"/"+uploadlawid);   
    java.lang.System.out.println(realPath+"/"+uploadlawid);
    if(saveDir.exists())
   {
      System.out.println("多级目录已经存在不需要创建！！");
    }else{
       System.out.println("如果要创建的多级目录不存在才需要创建。");
       saveDir.mkdirs();
      }
      System.out.println("startupload finish");
  //String path = getServletContext().getRealPath("/"); 
  ///System.out.println(getServletContext().getRealPath("/"));
  // 变量定义
  int count = 0;
  SmartUpload mySmartUpload = new SmartUpload();
  try {
   ///mySmartUpload.initialize(config, request, response);
   mySmartUpload.setMaxFileSize(20*1024*1024);
   mySmartUpload.upload();
   for (int i = 0; i < mySmartUpload.getFiles().getCount(); i++) {
    com.jspsmart.upload.File  myfile = mySmartUpload.getFiles().getFile(i);
    ext = mySmartUpload.getFiles().getFile(i).getFileExt().toLowerCase(); 
    ///myfile.saveAs("bbb.doc");
    String fileName = myfile.getFileName();
     upDocName = System.currentTimeMillis();



    //mySmartUpload.getFiles().getFile(i).saveAs("/upload/" + upDocName + "." + ext);
    mySmartUpload.getFiles().getFile(i).saveAs("/upload/"+uploadlawid+"/" + upDocName + "." + ext);
    System.out.println("/upload/"+uploadlawid+"/" + upDocName + "." + ext);
   }
  } catch (Exception e) {
   out.println("Unable to upload the file.<br>");
   out.println("Error : " + e.toString());
   System.out.println(e.toString());
  }
  System.out.println("upload finish");
  out.println("<script type='text/javascript' >parent.uploadSuccess('"+uploadlawid+"','"+"upload/"+uploadlawid+"/" + upDocName + "." + ext+"');SubWindow.hideParent();</script>");
  out.println("</d:View>");
  out.println("</BODY>");
  out.println("</HTML>");
 }
 protected void doPost(HttpServletRequest request,
   HttpServletResponse response) throws ServletException, IOException {
  // TODO Auto-generated method stub
  doGet(request, response);
 }
}

