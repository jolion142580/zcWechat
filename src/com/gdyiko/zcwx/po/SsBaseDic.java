package com.gdyiko.zcwx.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.gdyiko.tool.po.GenericPo;

/**
 * SsBaseDic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ss_base_dic")
public class SsBaseDic extends GenericPo implements java.io.Serializable {

    // Fields

    private String id;
    private String cname;
    private String iconPath;
    private String baseDicId;
    private String baseDicType;
    private String valid;
    private String sort;

    // Constructors

    /**
     * default constructor
     */
    public SsBaseDic() {
    }

    /**
     * minimal constructor
     */
    public SsBaseDic(String id) {
        this.id = id;
    }

    /**
     * full constructor
     */
    public SsBaseDic(String id, String cname, String iconPath) {
        this.id = id;
        this.cname = cname;
        this.iconPath = iconPath;
    }

    @Column(name = "sort", length = 255)
    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Column(name = "valid", length = 50)
    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
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

    @Column(name = "cname", length = 50)
    public String getCname() {
        return this.cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }


    @Column(name = "iconPath", length = 50)
    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }


    public String getBaseDicId() {
        return baseDicId;
    }

    public void setBaseDicId(String baseDicId) {
        this.baseDicId = baseDicId;
    }

    public String getBaseDicType() {
        return baseDicType;
    }

    public void setBaseDicType(String baseDicType) {
        this.baseDicType = baseDicType;
    }

    @Transient
    @Override
    public String getCreator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setCreator(String id) {
        // TODO Auto-generated method stub

    }

    @Transient
    @Override
    public String getCreattime() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setCreattime(String creattime) {
        // TODO Auto-generated method stub

    }

}