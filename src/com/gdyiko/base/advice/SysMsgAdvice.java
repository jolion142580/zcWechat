package com.gdyiko.base.advice;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.gdyiko.base.po.MemGeninf;
import com.gdyiko.base.po.SysMsgInfo;
import com.gdyiko.base.service.SysMsgInfoService;
import com.gdyiko.tool.PrimaryProduce;
import com.gdyiko.tool.SysContent;
import com.gdyiko.tool.po.GenericPo;

@Aspect
public class SysMsgAdvice {

	/*
	 * 
	 * 
	 * •Object[] getArgs：返回目标方法的参数
	 * 
	 * 
	 * •Signature getSignature：返回目标方法的签名
	 * 
	 * 
	 * •Object getTarget：返回被织入增强处理的目标对象
	 * 
	 * 
	 * •Object getThis：返回AOP框架为目标对象生成的代理对象
	 * 
	 * 
	 * *
	 */
	@Resource(name = "sysMsgInfoService")
	SysMsgInfoService sysMsgInfoService;

	@Pointcut("execution(* com.gdyiko.*.service.impl.*.*(..))")
	private void anyMethod() {
	}// 定义一个切入点

	/*
	 * @Before("execution(* com.gdyiko.*.service.impl.*.*(..))") public void
	 * permissionCheck(JoinPoint point) {
	 * 
	 * System.out.println("@Before：模拟权限检查...");
	 * System.out.println("@Before：目标方法为：" +
	 * point.getSignature().getDeclaringTypeName() + "." +
	 * point.getSignature().getName()); System.out.println("@Before：参数为：" +
	 * Arrays.toString(point.getArgs()));
	 * System.out.println("@Before：被织入的目标对象为：" + point.getTarget()); HttpSession
	 * session = SysContent.getSession(); MemGeninf user= (MemGeninf)
	 * session.getAttribute("user");
	 * ///System.out.println("@Before：session 中獲得：" + user); if(user!=null){
	 * System.out.println("@Before：session 中獲得：" + user.getLoginid()); }
	 * 
	 * }
	 */

	// 设置创建人 用登陆账号进行设置
	@Around("execution(* com.gdyiko.*.dao.impl.*.save(..))")
	public void setCreator(ProceedingJoinPoint point) throws Throwable {

		System.out.println("@Around：模拟权限检查...");
		System.out.println("@Around：目标方法为："
				+ point.getSignature().getDeclaringTypeName() + "."
				+ point.getSignature().getName());
		System.out.println("@Around：参数为：" + Arrays.toString(point.getArgs()));
		System.out.println("@Around：被织入的目标对象为：" + point.getTarget());
		try {
			HttpSession session = SysContent.getSession();
			MemGeninf user = (MemGeninf) session.getAttribute("user");
			// /System.out.println("@Before：session 中獲得：" + user);
			if (user != null) {
				System.out.println("@Around：session 中獲得：" + user.getLoginid());
			}
			Object[] objs = point.getArgs();
			if (objs.length == 1) {
				System.out.println("@Around：" + (objs[0] instanceof GenericPo));
				if (objs[0] instanceof GenericPo) {
					GenericPo gpobj = (GenericPo) objs[0];
					if (user != null) {
						gpobj.setCreator(user.getLoginid());
					} else {
						gpobj.setCreator("administrator");
					}
					objs[0] = gpobj;
				}
			}
			point.proceed(objs);
		} catch (Exception ex) {
			point.proceed();
		}
	}

	// 登陆日志
	@Before("execution(* com.gdyiko.*.service.impl.LoginServiceImpl.loginVerify(..))")
	public void login(JoinPoint point) {
		System.out.println("@Before：模拟权限检查...");
		System.out.println("@Before：目标方法为："
				+ point.getSignature().getDeclaringTypeName() + "."
				+ point.getSignature().getName());
		System.out.println("@Before：参数为：" + Arrays.toString(point.getArgs()));
		System.out.println("@Before：被织入的目标对象为：" + point.getTarget());
		Object[] args = point.getArgs();
		saveSysMsg("系统管理员", "账号“" + args[0] + "”正尝试登陆！", "administrator",
				false, false, false);
	}

	// 登陆成功日志
	@AfterReturning(pointcut = "execution(* com.gdyiko.*.service.impl.LoginServiceImpl.loginVerify(..))", returning = "returnValue")
	public void loginRecord(JoinPoint point, Object returnValue) {
		System.out.println("@AfterReturning：模拟日志记录功能...");
		System.out.println("@AfterReturning：目标方法为："
				+ point.getSignature().getDeclaringTypeName() + "."
				+ point.getSignature().getName());
		System.out.println("@AfterReturning：参数为："
				+ Arrays.toString(point.getArgs()));
		System.out.println("@AfterReturning：返回值为：" + returnValue);
		System.out.println("@AfterReturning：被织入的目标对象为：" + point.getTarget());
		MemGeninf u = (MemGeninf) returnValue;
		if (u != null) {
			saveSysMsg("系统管理员", "用户“" + u.getUsername() + "”登陆成功！",
					"administrator", false, false, false);
		}
	}

	// 接口 接收数据日志
	// add by xjd 20160809

	@Before("execution(* com.gdyiko.*.service.impl.SsFoodSamplingInfoServiceImpl.pushDateVerify(..))")
	public void beforePushRecord(JoinPoint point) {
		System.out.println("@Before：参数为：" + Arrays.toString(point.getArgs()));
		Object[] args = point.getArgs();
		saveSysMsg(args[1].toString(), args[0].toString(), "administrator");
	}

	@Before("execution(* com.gdyiko.*.service.impl.SsFoodSamplingInfoServiceImpl.afterPushDateVerify(..))")
	public void afterPushRecord(JoinPoint point) {
		System.out.println("@Before：参数为：" + Arrays.toString(point.getArgs()));
		Object[] args = point.getArgs();
		//updateSysMsg(args[0].toString(), args[1].toString());
	}

	// end 接口 接收数据日志

	public SysMsgInfoService getSysMsgInfoService() {
		return sysMsgInfoService;
	}

	public void setSysMsgInfoService(SysMsgInfoService sysMsgInfoService) {
		this.sysMsgInfoService = sysMsgInfoService;
	}

	public void saveSysMsg(String creater, String message, String msguser,
			boolean isMsn, boolean isRead, boolean isSend) {

		SysMsgInfo sysMsgInfo = new SysMsgInfo();
		String id = PrimaryProduce.produce();
		sysMsgInfo.setId(id);
		sysMsgInfo.setCreater(creater);
		sysMsgInfo.setMessage(message);
		sysMsgInfo.setMsguser(msguser);
		sysMsgInfo.setIsMsn(isMsn);
		sysMsgInfo.setIsRead(isRead);
		sysMsgInfo.setIsSend(isSend);
		sysMsgInfoService.save(sysMsgInfo);

	}

	public void saveSysMsg(String id, String message, String msguser) {

		SysMsgInfo sysMsgInfo = new SysMsgInfo();
		sysMsgInfo.setId(id);
		sysMsgInfo.setCreater("administrator");
		sysMsgInfo.setMessage(message);
		sysMsgInfo.setMsguser(msguser);
		sysMsgInfo.setIsMsn(false);
		sysMsgInfo.setIsRead(false);
		sysMsgInfo.setIsSend(false);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String dtime = df.format(new Date());
		sysMsgInfo.setCreattime(dtime);

		sysMsgInfoService.save(sysMsgInfo);

	}

	/*public void updateSysMsg(String id, String rbmessage) {
		SysMsgInfo sysMsgInfo = sysMsgInfoService.findById(id);
		sysMsgInfo.setRbMessage(rbmessage);
		sysMsgInfoService.modify(sysMsgInfo);

	}*/
	/*
	 * 学习例子
	 * 
	 * @Around("execution(* com.abc.service.*.many*(..))") public Object
	 * process(ProceedingJoinPoint point) throws Throwable {
	 * System.out.println("@Around：执行目标方法之前..."); //访问目标方法的参数： Object[] args =
	 * point.getArgs(); if (args != null && args.length > 0 &&
	 * args[0].getClass() == String.class) { args[0] = "改变后的参数1"; }
	 * //用改变后的参数执行目标方法 Object returnValue = point.proceed(args);
	 * System.out.println("@Around：执行目标方法之后...");
	 * System.out.println("@Around：被织入的目标对象为：" + point.getTarget()); return
	 * "原返回值：" + returnValue + "，这是返回结果的后缀"; }
	 * 
	 * @Before("execution(* com.abc.service.*.many*(..))") public void
	 * permissionCheck(JoinPoint point) {
	 * System.out.println("@Before：模拟权限检查...");
	 * System.out.println("@Before：目标方法为：" +
	 * point.getSignature().getDeclaringTypeName() + "." +
	 * point.getSignature().getName()); System.out.println("@Before：参数为：" +
	 * Arrays.toString(point.getArgs()));
	 * System.out.println("@Before：被织入的目标对象为：" + point.getTarget()); }
	 * 
	 * @AfterReturning(pointcut="execution(* com.abc.service.*.many*(..))",
	 * returning="returnValue") public void log(JoinPoint point, Object
	 * returnValue) { System.out.println("@AfterReturning：模拟日志记录功能...");
	 * System.out.println("@AfterReturning：目标方法为：" +
	 * point.getSignature().getDeclaringTypeName() + "." +
	 * point.getSignature().getName());
	 * System.out.println("@AfterReturning：参数为：" +
	 * Arrays.toString(point.getArgs()));
	 * System.out.println("@AfterReturning：返回值为：" + returnValue);
	 * System.out.println("@AfterReturning：被织入的目标对象为：" + point.getTarget());
	 * 
	 * }
	 * 
	 * @After("execution(* com.abc.service.*.many*(..))") public void
	 * releaseResource(JoinPoint point) {
	 * System.out.println("@After：模拟释放资源...");
	 * System.out.println("@After：目标方法为：" +
	 * point.getSignature().getDeclaringTypeName() + "." +
	 * point.getSignature().getName()); System.out.println("@After：参数为：" +
	 * Arrays.toString(point.getArgs())); System.out.println("@After：被织入的目标对象为："
	 * + point.getTarget()); }
	 */

}
