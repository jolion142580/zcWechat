package com.gdyiko.zcwx.service;
/**
 * 专门调用外部的接口
 * @author as
 *
 */
public interface InterfaceService {
	//预约签到接口
	public String signIn(String street,String id_card,String booking_no,String phone);
	//预约接口
	public String yuYues(String street,String service_type,String name,String id_card,String phone,String date,String s_time,String e_time,String openid,String businessName,int weight);
	//业务事项清单查询接口
	public String businessList(String department);
	//排队情况查询
	public String queueQuery(String street);
	//上传街镇排队情况查询接口
	public String townQueueQuery(String street,String queues);
	//网点预约情况查询接口
	public String onlineQuery(String street,String date,String s_time,String e_time,String businessType,int weight);
	//取消预约接口
	public String cancelYuYues(String booking_no,String id_card);
	//字典查询接口
	public String dictionary(String type);
	//查询预约状态信息
	public String yuYuesState(String booking_no,String id_card);
}
