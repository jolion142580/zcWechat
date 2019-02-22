package com.gdyiko.zcwx.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.gdyiko.tool.po.GenericPo;

/**
 * SsAffairMaterials entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ss_affair_materials")
public class SsAffairMaterials extends GenericPo implements java.io.Serializable {

    // Fields

    private String id;
    private String affairid;
    private String tableid;
    private String examplepath;
    private String istop;
    private String imageNum;
    private String matname;
    private String remarks;
    private String mattype;
    private String imageInfo;
    private String emptytablepath;
    private String matgroup;
    private String valid;
    private String materialcode;
    private String required;
    private String matnumber;
    private String ismust;
    private String reusetypeid;
    private String reusedetail;
    private String matindex;
    private String validdate;
    private String localpath;

    // Constructors

    /**
     * default constructor
     */
    public SsAffairMaterials() {
    }

    /**
     * full constructor
     */
    public SsAffairMaterials(String id, String affairid, String tableid,
                             String examplepath, String istop, String imageNum, String matname,
                             String remarks, String mattype, String imageInfo,
                             String emptytablepath, String matgroup, String valid,
                             String materialcode, String required, String matnumber,
                             String ismust, String reusetypeid, String reusedetail,
                             String matindex, String validdate) {
        this.id = id;
        this.affairid = affairid;
        this.tableid = tableid;
        this.examplepath = examplepath;
        this.istop = istop;
        this.imageNum = imageNum;
        this.matname = matname;
        this.remarks = remarks;
        this.mattype = mattype;
        this.imageInfo = imageInfo;
        this.emptytablepath = emptytablepath;
        this.matgroup = matgroup;
        this.valid = valid;
        this.materialcode = materialcode;
        this.required = required;
        this.matnumber = matnumber;
        this.ismust = ismust;
        this.reusetypeid = reusetypeid;
        this.reusedetail = reusedetail;
        this.matindex = matindex;
        this.validdate = validdate;
    }

    @Column(name = "localpath", length = 255)
    public String getLocalpath() {
        return localpath;
    }

    public void setLocalpath(String localpath) {
        this.localpath = localpath;
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

    @Column(name = "affairid", length = 50)
    public String getAffairid() {
        return this.affairid;
    }

    public void setAffairid(String affairid) {
        this.affairid = affairid;
    }

    @Column(name = "tableid", length = 50)
    public String getTableid() {
        return this.tableid;
    }

    public void setTableid(String tableid) {
        this.tableid = tableid;
    }

    @Column(name = "examplepath", length = 50)
    public String getExamplepath() {
        return this.examplepath;
    }

    public void setExamplepath(String examplepath) {
        this.examplepath = examplepath;
    }

    @Column(name = "istop", length = 50)
    public String getIstop() {
        return this.istop;
    }

    public void setIstop(String istop) {
        this.istop = istop;
    }

    @Column(name = "image_num", length = 50)
    public String getImageNum() {
        return this.imageNum;
    }

    public void setImageNum(String imageNum) {
        this.imageNum = imageNum;
    }

    @Column(name = "matname", length = 2000)
    public String getMatname() {
        return this.matname;
    }

    public void setMatname(String matname) {
        this.matname = matname;
    }

    @Column(name = "remarks", length = 2000)
    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Column(name = "mattype", length = 50)
    public String getMattype() {
        return this.mattype;
    }

    public void setMattype(String mattype) {
        this.mattype = mattype;
    }

    @Column(name = "image_info", length = 50)
    public String getImageInfo() {
        return this.imageInfo;
    }

    public void setImageInfo(String imageInfo) {
        this.imageInfo = imageInfo;
    }

    @Column(name = "emptytablepath", length = 50)
    public String getEmptytablepath() {
        return this.emptytablepath;
    }

    public void setEmptytablepath(String emptytablepath) {
        this.emptytablepath = emptytablepath;
    }

    @Column(name = "matgroup", length = 50)
    public String getMatgroup() {
        return this.matgroup;
    }

    public void setMatgroup(String matgroup) {
        this.matgroup = matgroup;
    }

    @Column(name = "valid", length = 50)
    public String getValid() {
        return this.valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    @Column(name = "materialcode", length = 50)
    public String getMaterialcode() {
        return this.materialcode;
    }

    public void setMaterialcode(String materialcode) {
        this.materialcode = materialcode;
    }

    @Column(name = "required", length = 50)
    public String getRequired() {
        return this.required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    @Column(name = "matnumber", length = 50)
    public String getMatnumber() {
        return this.matnumber;
    }

    public void setMatnumber(String matnumber) {
        this.matnumber = matnumber;
    }

    @Column(name = "ismust", length = 50)
    public String getIsmust() {
        return this.ismust;
    }

    public void setIsmust(String ismust) {
        this.ismust = ismust;
    }

    @Column(name = "reusetypeid", length = 50)
    public String getReusetypeid() {
        return this.reusetypeid;
    }

    public void setReusetypeid(String reusetypeid) {
        this.reusetypeid = reusetypeid;
    }

    @Column(name = "reusedetail", length = 1073741823)
    public String getReusedetail() {
        return this.reusedetail;
    }

    public void setReusedetail(String reusedetail) {
        this.reusedetail = reusedetail;
    }

    @Column(name = "matindex", length = 50)
    public String getMatindex() {
        return this.matindex;
    }

    public void setMatindex(String matindex) {
        this.matindex = matindex;
    }

    @Column(name = "validdate", length = 50)
    public String getValiddate() {
        return this.validdate;
    }

    public void setValiddate(String validdate) {
        this.validdate = validdate;
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

    @Override
    @Transient
    public String getCreattime() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setCreattime(String creattime) {
        // TODO Auto-generated method stub

    }

}