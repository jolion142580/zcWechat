package com.gdyiko.zcwx.po;

import com.gdyiko.tool.po.GenericPo;

import javax.persistence.*;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "black_white_list")
public class BlackWhiteList extends GenericPo implements java.io.Serializable{

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "openid")
    private String openid;

    @Column(name = "name")
    private String name;

    @Column(name = "id_card")
    private String idCard;

    @Column(name = "phone")
    private String phone;

    @Column(name = "time")
    private String time;

    @Column(name="flag")
    private String flag;

    @Column(name="forever")
    private String forever;

    @Column(name="creator")
    private String creator;

    @Column(name="creattime")
    private String creattime;

    @Column(name="updater")
    private String updater;

    @Column(name="updatetime")
    private String updatetime;

    @Override
    public String getId() {
        return id;
    }
    @Override
    public void setId(String id) {
        this.id = id == null ? "" : id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? "" : openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? "" : idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? "" : phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? "" : time;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? "" : flag;
    }

    public String getForever() {
        return forever;
    }

    public void setForever(String forever) {
        this.forever = forever == null ? "" : forever;
    }
    @Override
    public String getCreator() {
        return creator;
    }
    @Override
    public void setCreator(String creator) {
        this.creator = creator == null ? "" : creator;
    }
    @Override
    public String getCreattime() {
        return creattime;
    }
    @Override
    public void setCreattime(String creattime) {
        this.creattime = creattime == null ? "" : creattime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? "" : updater;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime == null ? "" : updatetime;
    }
}
