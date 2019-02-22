package com.gdyiko.zcwx.service;

import com.gdyiko.zcwx.po.Complaint;

import java.util.List;

import com.gdyiko.tool.service.GenericService;

public interface ComplaintService extends GenericService<Complaint, String> {
	/*
	 * public void saveByElement(String complaint_YwOrPeo,String
	 * complaint_Content,String complaint_Pho,String complaintTime, String
	 * complaint_Unit,String complaint_Num,String complaint_Title,String
	 * complaint_Status, String complaint_Remark,String complaint_Name,String
	 * complaint_Show);
	 */

	public void saveByElement(String complaint_Content, String complaint_Pho, String complaintTime,
			String complaint_Num, String complaint_Title, String complaint_Status, String complaint_Show,String open_Id);

	public List<Complaint> findByConditionShow(String openId,String complaintShow);
}
