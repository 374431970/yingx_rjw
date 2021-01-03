package com.cn.ascept;

import com.cn.annotation.AddLog;
import com.cn.dao.LogDao;
import com.cn.entity.Admin;
import com.cn.entity.Log;
import com.cn.util.UUIDUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author LogAscept
 * @time 2020/12/24-15:53
 */
@Aspect //切面类
@Configuration  //配置类  该类交给工厂管理
public class LogAscept {
    @Autowired
    LogDao logDao;
    @Resource
    HttpServletRequest request;

    //切增删改
    //@Around("execution( * com.cn.ServiceImpl.*.*(..)) && !execution(* com.baizhi.serviceImpl.*.query*(..)) ")  切方法
    @Around("@annotation(com.cn.annotation.AddLog)")
    public Object AddLog(ProceedingJoinPoint proceedingJoinPoint){

        System.out.println("= 1 ==进入环绕通知===");

        //谁  时间  操作(哪个方法)   是否成功

        //获取管理员
        Admin admin = (Admin)request.getSession().getAttribute("admin");

        //获取方法名
        String methodName = proceedingJoinPoint.getSignature().getName();


        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        //获取方法
        Method method = signature.getMethod();
        //通过类对象获取注解类
        AddLog addLog = method.getAnnotation(AddLog.class);
        //获取方法注解上的value属性的描述信息
        String methodDescription = addLog.value();

        Object result=null;
        String message=null;
        try {
            //放行方法  获取方法的返回值
            result = proceedingJoinPoint.proceed();

            message="success";
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            message="error";
        }

        System.out.println("= 3 ==回到环绕通知继续执行===");
        //UUIDUtil.getUUID(),admin.getName(),new Date(),methodName+"("+methodDescription+")",message
        //日志信息入库
        Log log = new Log();

        log.setId(UUIDUtil.getUUID());
        log.setLogName(admin.getName());
        log.setLogTimes(new Date());
        log.setStatus(message);
        log.setLogOption(methodName+"("+methodDescription+")");

        logDao.insertSelective(log);
        //返回返回值
        return result;
    }

}
