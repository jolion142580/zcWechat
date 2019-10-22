package com.gdyiko.zcwx.service.impl;

import com.gdyiko.base.service.PropertieService;
import com.gdyiko.tool.*;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;
import com.gdyiko.zcwx.dao.FileInfoDao;
import com.gdyiko.zcwx.po.FileInfo;
import com.gdyiko.zcwx.service.FileInfoService;
import com.gdyiko.zcwx.weixinUtils.TokenHepl;
import com.gdyiko.zcwx.weixinUtils.WxJSSignUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

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

    //    @Override
//    public void uploadByIdCardOrMaterials(String openId, String onlineApplyId, String mediaId, String remark, String materialId) {
    public JSONObject uploadByIdCardOrMaterials(File file, FileInfo fileInfo) {

        JSONObject reJson = new JSONObject();

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
    /*    byte[] data = new byte[1024];
        int len = 0;*/
        //FileOutputStream fileOutputStream = null;
     //new File(uploadPath+fileName);//getMedia(fileInfo.getMediaId());
        String filepath = uploadPath+fileName;
        FileUtil.copy(file,new File(filepath));
        //InputStream inputStream = null;
        try {
            //inputStream = new FileInputStream(filepath);
            //fileOutputStream = new FileOutputStream(uploadPath + fileName);
            //System.out.println();
          /*  while ((len = inputStream.read(data)) != -1) {
                fileOutputStream.write(data, 0, len);
            }*/
            //上传缩略图
            String reduceImgFileName = randomStr + "_reduce.jpg";
            ReduceImg.reduceImg(filepath, uploadPath + reduceImgFileName, 0, 0, 0.7f);
            //fileOutputStream.close();

            fileInfo.setId(PrimaryProduce.produce());
            fileInfo.setFilename(reduceImgFileName);
            fileInfo.setLocalpath(uploadPath + reduceImgFileName);
            fileInfo.setCreattime(DateUtil.getDateStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
            fileInfoDao.save(fileInfo);

            reJson.put("filepath","WeChatUpload!IoReadImage?myid="+fileInfo.getId());
            reJson.put("res",true);

        } catch (Exception e) {
            FileInfo delFileinfo = new FileInfo();
            delFileinfo.setMediaId(fileInfo.getMediaId());
            fileInfoDao.remove(delFileinfo);
            e.printStackTrace();
            reJson.put("res",false);
          //  return reJson;
        }

        return reJson;
    }


    /**
     * 获取临时素材
     */
/*    private InputStream getMedia(String mediaId) {
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
    }*/

    /**
     * @param openid
     * @return @
     * -------- 事项名称--------
     * |  ------备注1---------- |
     * | | ...图片...         | |
     * | ---------------------- |
     * | -------备注2---------  |
     * | |  ...图片...       |  |
     * |---------------------  |
     * -------------------------
     */
    public List<Map<String, List<Map<String, List<Map<String, String>>>>>> informationToList(String openid) {
        List<Map<String, List<Map<String, List<Map<String, String>>>>>> result = new LinkedList<Map<String, List<Map<String, List<Map<String, String>>>>>>();
        List<Map<String, String>> list = fileInfoDao.informationToList(openid);
        if (list.size() == 0) {
            return result;
        }
        //事务分类
        Set<String> onlineApplyS = new LinkedHashSet<String>();

        for (Map<String, String> map : list) {
            onlineApplyS.add(map.get("onlineApplyId")); //记录事务ID
        }

        for (String applyId : onlineApplyS) {

            //一个事项 对应的上传资料备注分类
            Map<String, List<Map<String, List<Map<String, String>>>>> onlineApplyMap = new LinkedHashMap<String, List<Map<String, List<Map<String, String>>>>>();

            List<Map<String, List<Map<String, String>>>> onLineApplyList = new LinkedList<Map<String, List<Map<String, String>>>>();
            List<Map<String, String>> temp = new LinkedList<Map<String, String>>();
            //备注分类
            Set<String> remarkS = new LinkedHashSet<String>();
            //事务名称
            String affairname = null;
            //事务申请时间
            String creattime = null;
            for (Map<String, String> map : list) {
                String onlineApplyId = map.get("onlineApplyId");
                if (onlineApplyId.equals(applyId)) {
                    temp.add(map);
                    remarkS.add(map.get("remark")); //记录备注分类
                    affairname = map.get("affairname"); //记录事务名称
                    creattime = map.get("creattime");
                }
            }
            for (String remark : remarkS) {
                Map<String, List<Map<String, String>>> remarkMap = new LinkedHashMap<String, List<Map<String, String>>>();
                List<Map<String, String>> remarkList = new LinkedList<Map<String, String>>();
                for (Map<String, String> map : temp) {
                    String r = map.get("remark");
                    if (r.equals(remark)) {
                        remarkList.add(map);
                    }
                }
                remarkMap.put(remark, remarkList);
                onLineApplyList.add(remarkMap);

            }
            onlineApplyMap.put("[ " + affairname + " ] 申请时间为：" + creattime, onLineApplyList);
            result.add(onlineApplyMap);
        }
        return result;
    }

    public List<Map<String, List<FileInfo>>> listByOnlineApply(String onlineApplyId) {
        List<Map<String, List<FileInfo>>> result = new LinkedList<Map<String, List<FileInfo>>>();
        FileInfo model = new FileInfo();
        model.setOnlineApplyId(onlineApplyId);
        List<FileInfo> fileInfoList = this.findEqualByEntity(model, BeanUtilEx.getNotNullPropertyNames(model));
        Set<String> remarkSet = new LinkedHashSet<String>();
        for (FileInfo fileInfo : fileInfoList) {
            if (fileInfo.getFilename().indexOf(".pdf") != -1) {
                continue;
            }
            remarkSet.add(fileInfo.getRemark());

        }
        for (String set : remarkSet) {
            Map<String, List<FileInfo>> resultMap = new LinkedHashMap<String, List<FileInfo>>();
            List<FileInfo> temp = new LinkedList<FileInfo>();
            for (FileInfo fileInfo : fileInfoList) {
                if (fileInfo.getRemark().equals(set)) {
                    temp.add(fileInfo);
                }
            }
            resultMap.put(set, temp);
            result.add(resultMap);
        }

        return result;
    }

    public String saveImg(File file,FileInfo fileInfo) {
        String prefix = System.currentTimeMillis() + WxJSSignUtil.getNonceStr();
        String mediaId =WxJSSignUtil.getMediaId(); //返回结果
        String fileName = prefix +".jpg";
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
        FileUtil.copy(file,new File(uploadPath+fileName));
        fileInfo.setId(PrimaryProduce.produce());
        fileInfo.setFilename(fileName);
        fileInfo.setLocalpath(uploadPath + fileName);
        fileInfo.setMediaId(mediaId);
        fileInfo.setCreattime(DateUtil.getDateStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        fileInfoDao.save(fileInfo);
        return mediaId;
    }

}
