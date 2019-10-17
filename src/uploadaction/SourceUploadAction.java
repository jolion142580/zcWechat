package uploadaction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class SourceUploadAction extends HttpServlet {
	
	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* 文件处理正式开始 */
		String value = "";
		HttpSession session = request.getSession();
		
        // 实例化一个硬盘文件工厂,用来配置上传组件ServletFileUpload
        DiskFileItemFactory dfif = new DiskFileItemFactory();
        dfif.setSizeThreshold(4096);// 设置上传文件时用于临时存放文件的内存大小,这里是4K.多于的部分将临时存在硬盘

        // 用以上工厂实例化上传组件
        ServletFileUpload sfu = new ServletFileUpload(dfif);
        // sfu.setHeaderEncoding("UTF-8");

        List fileList = null;
        try {

            // 文件存放真正位置
            String uploadRealPath = request.getSession()
                    .getServletContext().getRealPath("/")
                    + "upload//";
            System.out.println(uploadRealPath);
            File UploadRealPath = new File(uploadRealPath);
            System.out.println(uploadRealPath);
            UploadRealPath.mkdirs();

            // 文件存放URL位置
            String uploadURLPath = request.getScheme() + "://"
                    + request.getServerName() + ":"
                    + request.getServerPort() + request.getContextPath()
                    + "/upload/";
            System.out.println(uploadURLPath);	
            fileList = sfu.parseRequest(request);
            // 从request得到 所有 上传域的列表
            Iterator it = fileList.iterator();
            // URL地址,下载链接

            while (it.hasNext()) {
                // fileItem共有四项sourcetitle,sourceartical,sourcefile,sourcepostsubmit
                FileItem fi = (FileItem) it.next();
                // 字段名
                String fieldName = fi.getFieldName();
                System.out.println("fieldName------------>"+fieldName);
                if (fi.isFormField()) {
                    // String fieldName = fi.getFieldName();
                    value = new String(fi.getString()// 上传资源　　可能会导致乱码
                            .getBytes("ISO-8859-1"), "UTF-8");
                   System.out.println("fi.getName()="+fi.getName());
                } else if (!fi.isFormField()) {
                    // 文件名
                    value = fi.getName();
                    System.out.println("value-->"+value);
                    value = value.substring((value.lastIndexOf("\\") + 1)).replace(" ", "_");
                    InputStream is = fi.getInputStream();
                    OutputStream os = new FileOutputStream(UploadRealPath
                            .getPath()
                            + "/" + value);
                    byte[] buffer = new byte[1024];
                    int read = 0;
                    while ((read = is.read(buffer, 0, 1024)) != -1) {
                        os.write(buffer, 0, read);
                    }
                    System.out.println("关闭数据流！！2");
                    is.close();
                    os.close();
                    session.setAttribute("filename", value);
                }

            }



        } catch (FileUploadException e) {// 处理文件尺寸过大异常
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
