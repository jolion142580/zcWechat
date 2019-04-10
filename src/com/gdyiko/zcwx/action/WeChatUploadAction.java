package com.gdyiko.zcwx.action;

import com.gdyiko.base.service.PropertieService;
import com.gdyiko.tool.BeanUtilEx;
import com.gdyiko.tool.action.BaseAction;
import com.gdyiko.tool.service.GenericService;
import com.gdyiko.zcwx.po.FileInfo;
import com.gdyiko.zcwx.service.FileInfoService;
import com.gdyiko.zcwx.weixinUtils.TokenHepl;
import com.gdyiko.zcwx.weixinUtils.TokenThread;
import net.sf.json.JSONArray;
import org.apache.commons.collections.CollectionUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.springside.modules.web.struts2.Struts2Utils;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

//@ParentPackage("custom-default")
@Namespace("/")
@Action(value = "WeChatUpload", results = {
// 成功
        @Result(name = "success", location = "/"),

        // 失败
        @Result(name = "fail", location = "/")

})
// interceptorRefs = {@InterceptorRef(value = "mydefault")})
public class WeChatUploadAction extends BaseAction<FileInfo, String> {

    /**
     *
     */
    private static final long serialVersionUID = 8321018464736012550L;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式

    HttpServletRequest request = null;


    @Resource(name = "propertieService")
    PropertieService propertieService;


    @Resource(name = "fileInfoService")
    FileInfoService fileInfoService;

    @Resource(name = "fileInfoService")
    @Override
    public void setGenericService(GenericService<FileInfo, String> genericService) {
        // TODO Auto-generated method stub
        super.setGenericService(genericService);
    }


    public WeChatUploadAction() {
        request = ServletActionContext.getRequest();
        // HttpServletRequest request =
        // (HttpServletRequest)ActionContext.getContext().get("request");
        // System.out.println(":::::::::"+request.getParameter("code"));
    }

    public void delFile() {

        List<FileInfo> fileInfoList = fileInfoService.findEqualByEntity(this.model, BeanUtilEx.getNotNullEscapePropertyNames(this.model));
        super.setMyid(fileInfoList.get(0).getId());
        super.remove();


        FileInfo fileInfo = new FileInfo();
        fileInfo.setOpenid(fileInfoList.get(0).getOpenid());
        fileInfo.setRemark(fileInfoList.get(0).getRemark());
        fileInfo.setOnlineApplyId(fileInfoList.get(0).getOnlineApplyId());
        List<FileInfo> fileList = fileInfoService.findEqualByEntity(fileInfo, BeanUtilEx.getNotNullEscapePropertyNames(fileInfo));
        System.out.println("==========================================================size" + fileList.size());
        if (fileList.size() < 3) {
            for (int i = 0; i < fileList.size(); i++) {
                if (fileList.get(i).getFilename().endsWith(".pdf")) {
                    System.out.println("==================================================================" + fileList.get(i).getFilename());
                    super.setMyid(fileList.get(i).getId());
                    super.remove();
                }
            }
        }
    }

    public void savePicture() throws JSONException {


        if(fileInfoService.uploadByIdCardOrMaterials(this.model)){
            Struts2Utils.renderText("{\"res\":\"true\"}");
            return;

        }else {
            Struts2Utils.renderText("{\"res\":\"false\"}");
            return;
        }


    }

    /**
     * 保存图片至服务器
     *
     * @param mediaId
     * @return 文件名
     */
//    public String saveImageToDisk() {
//
//        String filePath = propertieService.getPropertie("filePath") + this.model.getOpenid() + "/" + this.model.getOnlineApplyId() + "/";
//
//        if (this.model.getOnlineApplyId() == null || this.model.getOnlineApplyId().equals("")) {
//            filePath = propertieService.getPropertie("filePath") + this.model.getOpenid() + "/";
//            System.out.println("================================================" + filePath);
//        }
//
//        File saveDir = new File(filePath);
//        if (!saveDir.exists()) {//如果要创建的多级目录不存在才需要创建。
//            saveDir.mkdirs();
//        }
//        String filename = "";
//        InputStream inputStream = getMedia(this.model.getMediaId());
//        byte[] data = new byte[1024];
//        int len = 0;
//        FileOutputStream fileOutputStream = null;
//        try {
//            // 服务器存图路径
//            //String path = "c:/wechatfile/vehicleupload/";
//            String filenameVal = System.currentTimeMillis() + WxJSSignUtil.getNonceStr();
//            filename = filenameVal + ".jpg";
//
//            fileOutputStream = new FileOutputStream(filePath + filename);
//            while ((len = inputStream.read(data)) != -1) {
//                fileOutputStream.write(data, 0, len);
//            }
//            fileOutputStream.close();
//            String reduceImgFileName = filenameVal + "_reduce.jpg";
//
//            ReduceImg.reduceImg(filePath + filename, filePath + reduceImgFileName, 0, 0, 0.7f);
//
//            this.model.setId(PrimaryProduce.produce());
//            this.model.setFilename(filename);
//            this.model.setLocalpath(filePath + reduceImgFileName);
//            this.model.setCreattime(df.format(new java.util.Date()));
//            this.model.setRemark(this.model.getRemark().trim());
//            super.save();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (fileOutputStream != null) {
//                try {
//                    fileOutputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        //将身份证正反面转换成一张pdf
//        idCardToPdf(this.model.getOpenid(), this.model.getRemark(), filePath, this.model.getOnlineApplyId());
//        return this.model.getId();
//    }

//    private void idCardToPdf(String openId, String Remark, String filePath, String onlineApplyId) {
//        FileInfo fileInfo = new FileInfo();
//        if (onlineApplyId != null && !onlineApplyId.equals("")) {
//            fileInfo.setOnlineApplyId(onlineApplyId);
//            System.out.println("============onlineApplyId=================" + fileInfo.getOnlineApplyId());
//        }
//        fileInfo.setOpenid(openId);
//        fileInfo.setRemark(Remark);
//        List<FileInfo> fileInfoList = fileInfoService.findLikeByEntity(this.model, BeanUtilEx.getNotNullPropertyNames(fileInfo));
//        List<String> fileNameList = new ArrayList();
//
//        if (fileInfoList.size() >= 2 && fileInfoList != null) {
//            for (FileInfo info : fileInfoList) {
//
//                if (info.getLocalpath().indexOf("_reduce.jpg") != -1) {
//                    fileNameList.add(info.getLocalpath());
//                }
//            }
//
//            if (fileNameList != null && fileNameList.size() > 0) {
//                File file1 = new File(fileNameList.get(0));
//                File file2 = new File(fileNameList.get(1));
//                try {
//                    List<File> fileList = Arrays.asList(file1, file2);
//                    String fileName = this.model.getRemark() + ".pdf";
//                    System.out.println("================filename======================" + fileName);
//
//                    File target = new File(filePath + fileName);
//
//                    if (ImgPdfUtils.imgMerageToPdf(fileList, target)) {
//                        this.model.setId(PrimaryProduce.produce());
//                        this.model.setFilename(fileName);
//                        this.model.setLocalpath(filePath + fileName);
//                        this.model.setRemark(Remark);
//                        this.model.setCreattime(df.format(new java.util.Date()));
//                        super.save();
//                        System.out.println("==============save=================");
//                    } else {
//                        System.out.println("=======================savefail====================");
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    /**
     * 获取临时素材
     */
    private InputStream getMedia(String mediaId) {
        String url = "https://api.weixin.qq.com/cgi-bin/media/get";
        String access_token = TokenHepl.getaccessToken().getAccessToken();;
        String params = "access_token=" + access_token + "&media_id=" + mediaId;
        InputStream is = null;
        try {
            String urlNameString = url + "?" + params;
            System.out.println("-=-=-=-=" + urlNameString);
            URL urlGet = new URL(urlNameString);
            HttpURLConnection http = (HttpURLConnection) urlGet
                    .openConnection();
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            http.connect();
            // 获取文件转化为byte流
            is = http.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return is;
    }

    public String IoReadImage() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        String mediaId = request.getParameter("mediaId");
        HttpServletResponse response = ServletActionContext.getResponse();
        ServletOutputStream out = null;
        FileInputStream ips = null;
        try {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setMediaId(mediaId);

            List<FileInfo> fileInfoList = fileInfoService.findEqualByEntity(fileInfo, BeanUtilEx.getNotNullEscapePropertyNames(fileInfo));
            if (CollectionUtils.isNotEmpty(fileInfoList)) {


                System.out.println("-=-读取图片=-=-=" + fileInfoList.get(0).getLocalpath());
                File file = new File(fileInfoList.get(0).getLocalpath());
                if (file.exists()) {
                    //获取图片存放路径
                    String imgPath = fileInfoList.get(0).getLocalpath();
                    ips = new FileInputStream(new File(imgPath));
                    response.setContentType("multipart/form-data");
                    out = response.getOutputStream();
                    //读取文件流
                    int len = 0;
                    byte[] buffer = new byte[1024 * 10];
                    while ((len = ips.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                    out.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
            ips.close();
        }

        return null;

    }

    public String findByOpenId() {
        //获取文件上传信息
        try {
            //System.out.println("--openid---"+this.model.getOpenid());
//            System.out.println("---onlineApplyId-11111--" + this.model.getOnlineApplyId());
                /*if(this.model.getOnlineApplyId()==null){
                    this.model.setOnlineApplyId("onlineApplyId");//申请人办理事项
				}*/

            List<FileInfo> fileInfoList = fileInfoService.findEqualByEntity(this.model, BeanUtilEx.getNotNullEscapePropertyNames(this.model));
            System.out.println("【查找图片，长度为】" + fileInfoList.size());
            System.out.println("【查找图片，传入的参数为】：【" + this.model.toString() + "】");
            List<FileInfo> newFileInfoList = new ArrayList<FileInfo>();

            if (CollectionUtils.isNotEmpty(fileInfoList)) {
                for (FileInfo fileInfo : fileInfoList) {
                    if (!fileInfo.getLocalpath().endsWith(".pdf")) {
                        newFileInfoList.add(fileInfo);
                    }
                }

                JSONArray ja = JSONArray.fromObject(newFileInfoList);
                Struts2Utils.renderText(ja.toString());
            } else {
                return null;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}

