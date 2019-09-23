package com.gdyiko.zcwx.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gdyiko.tool.po.GenericPo;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "Days")
public class Days extends GenericPo implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = -2631267276622442880L;

    @Id
    @Column
    private String id;

    @Column
    private String name;

    @Column
    private String year;

    @Column
    private String days;




    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

}
