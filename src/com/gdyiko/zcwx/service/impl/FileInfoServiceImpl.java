package com.gdyiko.zcwx.service.impl;

import com.gdyiko.base.service.PropertieService;
import com.gdyiko.tool.DateUtil;
import com.gdyiko.tool.PrimaryProduce;
import com.gdyiko.tool.ReduceImg;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;
import com.gdyiko.zcwx.dao.FileInfoDao;
import com.gdyiko.zcwx.po.FileInfo;
import com.gdyiko.zcwx.service.FileInfoService;
import com.gdyiko.zcwx.weixinUtils.TokenHepl;
import com.gdyiko.zcwx.weixinUtils.TokenThread;
import com.gdyiko.zcwx.weixinUtils.WxJSSignUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

@Service("fileInfoService")
public class FileInfoServiceImpl extends
        GenericServiceImpl<FileInfo, String> implements
        FileInfoService {
    @Resource(name = "fileInfoDao")
    FileInfoDao fileInfoDao;

    @Resource(name = "propertieService")
    PropertieService propertieService;

    @Resource(name = "fileInfoDao")
    @Override
    public void setGenericDao(GenericDao<FileInfo, String> genericDao) {
        super.setGenericDao(genericDao);
    }

    @Override
//    public void uploadByIdCardOrMaterials(String openId, String onlineApplyId, String mediaId, String remark, String materialId) {
    public boolean uploadByIdCardOrMaterials(FileInfo fileInfo) {
        String uploadPath = propertieService.getPropertie("filePath") + fileInfo.getOpenid() + "/";
        //网上办事上传的路径

        if (StringUtils.isNotBlank(fileInfo.getOnlineApplyId())) {
            uploadPath += fileInfo.getOnlineApplyId() + "/";
        }

        System.out.println("【附件上传】附件地址：" + uploadPath);

        //创建附件路径
        File uploadFile = new File(uploadPath);
        if (!uploadFile.exists()) {
            uploadFile.mkdirs();
        }

        String randomStr = System.currentTimeMillis() + WxJSSignUtil.getNonceStr();
        String fileName = randomStr + ".jpg";
        byte[] data = new byte[1024];
        int len = 0;
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = getMedia(fileInfo.getMediaId());
        try {
            fileOutputStream = new FileOutputStream(uploadPath + fileName);
            System.out.println();
            while ((len = inputStream.read(data)) != -1) {
                fileOutputStream.write(data, 0, len);
            }
            //上传缩略图
            String reduceImgFileName = randomStr + "_reduce.jpg";
            ReduceImg.reduceImg(uploadPath + fileName, uploadPath + reduceImgFileName, 0, 0, 0.7f);
            fileOutputStream.close();

            fileInfo.setId(PrimaryProduce.produce());
            fileInfo.setFilename(reduceImgFileName);
            fileInfo.setLocalpath(uploadPath + reduceImgFileName);
            fileInfo.setCreattime(DateUtil.getDateStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
            fileInfoDao.save(fileInfo);
        } catch (Exception e) {
            FileInfo delFileinfo = new FileInfo();
            delFileinfo.setMediaId(fileInfo.getMediaId());
            fileInfoDao.remove(delFileinfo);
            e.printStackTrace();
            return false;
        } finally {

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }


    /**
     * 获取临时素材
     */
    private InputStream getMedia(String mediaId) {
        String url = "https://api.weixin.qq.com/cgi-bin/media/get";
        String access_token = TokenHepl.getaccessToken().getAccessToken();//TokenThread.accessToken.getAccessToken();
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
}
