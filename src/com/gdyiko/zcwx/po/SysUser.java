package com.gdyiko.zcwx.po;

import com.gdyiko.tool.po.GenericPo;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "sys_user", schema = "dbo", catalog = "QUEUE_ZC")
public class SysUser extends GenericPo implements java.io.Serializable {
    private static final long serialVersionUID = -5864429000146148355L;
    private long userId;
    private String username;
    private String name;
    private String password;
    private Long deptId;
    private String email;
    private String mobile;
    private Integer status;
    private Long userIdCreate;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
    private Long sex;
    private Timestamp birth;
    private Long picId;
    private String liveAddress;
    private String hobby;
    private String province;
    private String city;
    private String district;

    @Id
    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "dept_id")
    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "user_id_create")
    public Long getUserIdCreate() {
        return userIdCreate;
    }

    public void setUserIdCreate(Long userIdCreate) {
        this.userIdCreate = userIdCreate;
    }

    @Basic
    @Column(name = "gmt_create")
    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Basic
    @Column(name = "gmt_modified")
    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Basic
    @Column(name = "sex")
    public Long getSex() {
        return sex;
    }

    public void setSex(Long sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "birth")
    public Timestamp getBirth() {
        return birth;
    }

    public void setBirth(Timestamp birth) {
        this.birth = birth;
    }

    @Basic
    @Column(name = "pic_id")
    public Long getPicId() {
        return picId;
    }

    public void setPicId(Long picId) {
        this.picId = picId;
    }

    @Basic
    @Column(name = "live_address")
    public String getLiveAddress() {
        return liveAddress;
    }

    public void setLiveAddress(String liveAddress) {
        this.liveAddress = liveAddress;
    }

    @Basic
    @Column(name = "hobby")
    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Basic
    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "district")
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Transient
    @Override
    public String getId() {
        return null;
    }

    @Override
    public void setId(String id) {

    }

    @Transient
    @Override
    public String getCreator() {
        return null;
    }

    @Override
    public void setCreator(String id) {

    }
    @Transient
    @Override
    public String getCreattime() {
        return null;
    }

    @Override
    public void setCreattime(String creattime) {

    }
}
