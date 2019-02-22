package com.gdyiko.zcwx.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gdyiko.tool.po.GenericPo;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "YUYUES")
public class YuYues extends GenericPo implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9124526084625803232L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column
	private String id;
	@Column
	private String name;
	@Column
	private String idcard;
	@Column
	private String phone;
	@Column
	private String no;
	@Column
	private String street;
	@Column
	private String state;
	@Column
	private String stype;
	@Column
	private String terminal;
	@Column
	private String ystime;
	@Column
	private String yetime;
	@Column
	private String ydate;
	@Column
	private String cdate;
	@Column
	private String qdate;
	@Column
	private String timecode;
	@Column
	private String openid;
	@Column
	private Integer weight;
	@Column
	private String businessName;
	@Column
	private String appraiseResult;
	@Column
	private String appraiseAdvice;
	@Column
	private String queueNum;
	@Column
	private String USING;
	@Column
	private String allstart;
	@Column
	private String glstart;
	@Column
	private String fwstart;
	@Column
	private String tdstart;
	@Column
	private String UseAdvice;
	@Column
	private String OtherAdvice;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getYstime() {
		return ystime;
	}

	public void setYstime(String ystime) {
		this.ystime = ystime;
	}

	public String getYetime() {
		return yetime;
	}

	public void setYetime(String yetime) {
		this.yetime = yetime;
	}

	public String getYdate() {
		return ydate;
	}

	public void setYdate(String ydate) {
		this.ydate = ydate;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public String getQdate() {
		return qdate;
	}

	public void setQdate(String qdate) {
		this.qdate = qdate;
	}

	public String getTimecode() {
		return timecode;
	}

	public void setTimecode(String timecode) {
		this.timecode = timecode;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	@Override
	public String getCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreator(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCreattime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreattime(String creattime) {
		// TODO Auto-generated method stub
		
	}

	public String getAppraiseResult() {
		return appraiseResult;
	}

	public void setAppraiseResult(String appraiseResult) {
		this.appraiseResult = appraiseResult;
	}

	public String getAppraiseAdvice() {
		return appraiseAdvice;
	}

	public void setAppraiseAdvice(String appraiseAdvice) {
		this.appraiseAdvice = appraiseAdvice;
	}

	public String getQueueNum() {
		return queueNum;
	}

	public void setQueueNum(String queueNum) {
		this.queueNum = queueNum;
	}

	public String getUSING() {
		return USING;
	}

	public void setUSING(String uSING) {
		USING = uSING;
	}

	public String getAllstart() {
		return allstart;
	}

	public void setAllstart(String allstart) {
		this.allstart = allstart;
	}

	public String getGlstart() {
		return glstart;
	}

	public void setGlstart(String glstart) {
		this.glstart = glstart;
	}

	public String getFwstart() {
		return fwstart;
	}

	public void setFwstart(String fwstart) {
		this.fwstart = fwstart;
	}

	public String getTdstart() {
		return tdstart;
	}

	public void setTdstart(String tdstart) {
		this.tdstart = tdstart;
	}

	public String getUseAdvice() {
		return UseAdvice;
	}

	public void setUseAdvice(String useAdvice) {
		UseAdvice = useAdvice;
	}

	public String getOtherAdvice() {
		return OtherAdvice;
	}

	public void setOtherAdvice(String otherAdvice) {
		OtherAdvice = otherAdvice;
	}
	
}
