package com.gdyiko.zcwx.po;

import com.gdyiko.tool.po.GenericPo;

import javax.persistence.*;

/**
 * OnlineApply entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "onlineApply")
public class OnlineApply extends GenericPo implements java.io.Serializable {

    // Fields

    private String id;
    private String affairid;
    private String objindex;
    private String openid;
    private String onlineData;

//    private String onlineData1;
    private String iscommit;
    private String state;
    private String limitDate;
    //	private String depart;
//	private String remark;
    private String creattime;
    private String affairName;

    //审核通过标记   1、通过  0、失败
    private String approvedOrNot;


    // Constructors

    /**
     * default constructor
     */
    public OnlineApply() {
    }

    /**
     * minimal constructor
     */
    public OnlineApply(String affairid) {
        this.affairid = affairid;
    }

    /**
     * full constructor
     */
    public OnlineApply(String affairid, String objindex, String openid, String onlineData,
                       String iscommit, String state, String limitDate, String approvedOrNot, /*String depart,
            String remark, */String creattime) {
        this.affairid = affairid;
        this.objindex = objindex;
        this.openid = openid;
        this.onlineData = onlineData;
        this.iscommit = iscommit;
        this.state = state;
        this.limitDate = limitDate;
    /*	this.depart = depart;
        this.remark = remark;*/
        this.creattime = creattime;
        this.approvedOrNot = approvedOrNot;
    }

    // Property accessors
    @Id
    @Column(name = "id", unique = true, nullable = false, length = 50)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "affairid", nullable = false, length = 500)
    public String getAffairid() {
        return this.affairid;
    }

    public void setAffairid(String affairid) {
        this.affairid = affairid;
    }

    @Column(name = "objindex", nullable = false, length = 500)
    public String getObjindex() {
        return this.objindex;
    }

    public void setObjindex(String objindex) {
        this.objindex = objindex;
    }

    @Column(name = "openid", length = 500)
    public String getOpenid() {
        return this.openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Column(name = "onlineData", length = 1073741823)
    public String getOnlineData() {
        return this.onlineData;
    }

    public void setOnlineData(String onlineData) {
        this.onlineData = onlineData;
    }

    @Column(name = "iscommit", length = 500)
    public String getIscommit() {
        return iscommit;
    }

    public void setIscommit(String iscommit) {
        this.iscommit = iscommit;
    }

    @Column(name = "state", length = 500)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "limitDate", length = 50)
    public String getLimitDate() {
        return this.limitDate;
    }

    @Column(name = "approved_or_not")
    public String getApprovedOrNot() {
        return approvedOrNot;
    }

    public void setApprovedOrNot(String approvedOrNot) {
        this.approvedOrNot = approvedOrNot;
    }

    public void setLimitDate(String limitDate) {
        this.limitDate = limitDate;
    }

//	@Column(name = "depart", length = 500)
//	public String getDepart() {
//		return this.depart;
//	}
//
//	public void setDepart(String depart) {
//		this.depart = depart;
//	}
//
//	@Column(name = "remark", length = 1073741823)
//	public String getRemark() {
//		return this.remark;
//	}
//
//	public void setRemark(String remark) {
//		this.remark = remark;
//	}

    @Column(name = "creattime", length = 500)
    public String getCreattime() {
        return this.creattime;
    }

    public void setCreattime(String creattime) {
        this.creattime = creattime;
    }

    @Override
    @Transient
    public String getCreator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setCreator(String id) {
        // TODO Auto-generated method stub

    }

    @Transient
    public String getAffairName() {
        return affairName;
    }

    public void setAffairName(String affairName) {
        this.affairName = affairName;
    }


}