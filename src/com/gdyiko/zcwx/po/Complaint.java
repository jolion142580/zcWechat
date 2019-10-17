package com.gdyiko.zcwx.po;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.poi2.hssf.record.formula.functions.False;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import com.gdyiko.tool.po.GenericPo;
import org.hibernate.sql.Insert;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "complaint")
public class Complaint extends GenericPo implements Serializable {
    private static final long serialVersionUID = 1L;

    public Complaint() {
        // TODO Auto-generated constructor stub
    }

    // Fields
    @Id
    @Column
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
    private String c_Id;//id

    @Column
    private String complaint_Num;//编号


    @ManyToOne(cascade = CascadeType.ALL, targetEntity = SsUserInfo.class)
    @JoinColumn(name = "open_id", referencedColumnName = "id")
    private SsUserInfo ssUserInfo;

    @Column
    private String open_Id;

    @Column
    private String complaint_Content;//内容

    /*@Column
    private String complaint_Unit;//被投诉单位
*/
    @Column
    private String complaintTime;//投诉时间
    /*@Column
	private String complaint_Name;//投诉人
	*/

    @Column
    private String complaint_Status;//状态
    /*@Column
    private String complaint_YwOrPeo;//业务还是人员
    @Column
    private String complaint_Remark;//投诉或者评议
    @Column
    */
    private String complaint_Title;//摘要标题
    @Column
    private String complaint_Pho;//电话
    @Column
    private String complaint_Show;//是否审核
    @Column
    private String complaint_Reply;//回复内容
    @Column
    private String complaintReplyTime;//回复时间

    public String getC_Id() {
        return c_Id;
    }

    public void setC_Id(String c_Id) {
        this.c_Id = c_Id;
    }

    public String getComplaint_Num() {
        return complaint_Num;
    }

    public void setComplaint_Num(String complaint_Num) {
        this.complaint_Num = complaint_Num;
    }

    public String getComplaint_Content() {
        return complaint_Content;
    }

    public void setComplaint_Content(String complaint_Content) {
        this.complaint_Content = complaint_Content;
    }

    public String getComplaint_Status() {
        return complaint_Status;
    }

    public void setComplaint_Status(String complaint_Status) {
        this.complaint_Status = complaint_Status;
    }

    public String getComplaint_Title() {
        return complaint_Title;
    }

    public void setComplaint_Title(String complaint_Title) {
        this.complaint_Title = complaint_Title;
    }

    public String getComplaint_Pho() {
        return complaint_Pho;
    }

    public void setComplaint_Pho(String complaint_Pho) {
        this.complaint_Pho = complaint_Pho;
    }

    public String getComplaint_Show() {
        return complaint_Show;
    }

    public void setComplaint_Show(String complaint_Show) {
        this.complaint_Show = complaint_Show;
    }

    public String getComplaint_Reply() {
        return complaint_Reply;
    }

    public void setComplaint_Reply(String complaint_Reply) {
        this.complaint_Reply = complaint_Reply;
    }

    public String getComplaintReplyTime() {
        return complaintReplyTime;
    }

    public void setComplaintReplyTime(String complaintReplyTime) {
        this.complaintReplyTime = complaintReplyTime;
    }

    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setId(String id) {
        // TODO Auto-generated method stub

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

    public String getOpen_Id() {
        return open_Id;
    }

    public void setOpen_Id(String open_Id) {
        this.open_Id = open_Id;
    }

    public String getComplaintTime() {
        return complaintTime;
    }

    public void setComplaintTime(String complaintTime) {
        this.complaintTime = complaintTime;
    }

    public SsUserInfo getSsUserInfo() {
        return ssUserInfo;
    }

    public void setSsUserInfo(SsUserInfo ssUserInfo) {
        this.ssUserInfo = ssUserInfo;
    }
}
