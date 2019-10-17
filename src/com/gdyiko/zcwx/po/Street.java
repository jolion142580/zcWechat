package com.gdyiko.zcwx.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.gdyiko.tool.po.GenericPo;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "STREET")
public class Street extends GenericPo implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7984707101537940348L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column
	private String id;
	@Column
	private String name;
	@Column
	private String no;
	@Column
	private String yalimitnum1;
	@Column
	private String yalimitnum2;
	@Column
	private String yalimitnum3;
	@Column
	private String yalimitnum4;
	@Column
	private String yalimitnum5;
	@Column
	private String yalimitnum6;

	
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
	
	public String getNo() {
		return no;
	}
	
	public void setNo(String no) {
		this.no = no;
	}

	public String getYalimitnum1() {
		return yalimitnum1;
	}

	public void setYalimitnum1(String yalimitnum1) {
		this.yalimitnum1 = yalimitnum1;
	}

	public String getYalimitnum2() {
		return yalimitnum2;
	}

	public void setYalimitnum2(String yalimitnum2) {
		this.yalimitnum2 = yalimitnum2;
	}

	public String getYalimitnum3() {
		return yalimitnum3;
	}

	public void setYalimitnum3(String yalimitnum3) {
		this.yalimitnum3 = yalimitnum3;
	}

	public String getYalimitnum4() {
		return yalimitnum4;
	}

	public void setYalimitnum4(String yalimitnum4) {
		this.yalimitnum4 = yalimitnum4;
	}

	public String getYalimitnum5() {
		return yalimitnum5;
	}

	public void setYalimitnum5(String yalimitnum5) {
		this.yalimitnum5 = yalimitnum5;
	}

	public String getYalimitnum6() {
		return yalimitnum6;
	}

	public void setYalimitnum6(String yalimitnum6) {
		this.yalimitnum6 = yalimitnum6;
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
