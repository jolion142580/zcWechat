package com.gdyiko.zcwx.po;


import javax.persistence.*;

import com.gdyiko.tool.po.GenericPo;

import java.util.HashSet;
import java.util.Set;

/**
 * SsUserInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ss_user_info")
public class SsUserInfo extends GenericPo implements java.io.Serializable {

    // Fields

    private String id;
    private String name;
    private String idCard;
    private String phone;
    private String address;
    private String creattime;
    private String sex;
    private String brithday;
    // Constructors

    /**
     * default constructor
     */
    public SsUserInfo() {
    }

    /**
     * minimal constructor
     */
    public SsUserInfo(String id) {
        this.id = id;
    }

    public SsUserInfo(String id, String name, String idCard, String phone, String address, String creattime, String sex, String brithday) {
        this.id = id;
        this.name = name;
        this.idCard = idCard;
        this.phone = phone;
        this.address = address;
        this.creattime = creattime;
        this.sex = sex;
        this.brithday = brithday;
    }

    /**
     * full constructor
     */


    // Property accessors
    @Id
    @Column(name = "id", unique = true, nullable = false, length = 100)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "name", length = 50)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "idCard", length = 50)
    public String getIdCard() {
        return this.idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Column(name = "phone", length = 50)
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "address", length = 1000)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "creattime", length = 23)
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBrithday() {
        return brithday;
    }

    public void setBrithday(String brithday) {
        this.brithday = brithday;
    }
}