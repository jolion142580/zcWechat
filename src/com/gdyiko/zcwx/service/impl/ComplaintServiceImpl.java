package com.gdyiko.zcwx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdyiko.zcwx.dao.ComplaintDao;
import com.gdyiko.zcwx.po.Complaint;
import com.gdyiko.zcwx.service.ComplaintService;
import com.gdyiko.tool.dao.GenericDao;
import com.gdyiko.tool.service.impl.GenericServiceImpl;



@Service("complaintService")
public class 	ComplaintServiceImpl extends GenericServiceImpl<Complaint, String>implements ComplaintService {
	
	@Resource(name = "complaintDao")
	ComplaintDao complaintDao;
	
	@Resource(name = "complaintDao")
	@Override
	public void setGenericDao(GenericDao<Complaint, String> genericDao) {
		// TODO Auto-generated method stub
		super.setGenericDao(genericDao);
	}

	public void saveByElement(String complaint_Content,String complaint_Pho,String complaintTime,
			String complaint_Num,String complaint_Title,String complaint_Status,
			String complaint_Show,String open_Id) {
		Complaint complaint =new Complaint();
		
		complaint.setComplaint_Content(complaint_Content);
		complaint.setComplaint_Pho(complaint_Pho);
		complaint.setComplaintTime(complaintTime);
		complaint.setComplaint_Num(complaint_Num);
		complaint.setComplaint_Title(complaint_Title);
		complaint.setComplaint_Status(complaint_Status);
		complaint.setComplaint_Show(complaint_Show);
		complaint.setOpen_Id(open_Id);

		complaintDao.save(complaint);
	}

	public List<Complaint> findByConditionShow(String openId,String complaintShow) {
		
		return complaintDao.findByConditionShow(openId,complaintShow);
	}
	 

}