package com.itheima.ssm.controller;

import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 切面类处理日志
 */
@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;

    private Date visitTime;//访问的时间
    private Class clazz;//访问的类
    private Method method;//访问的方法

    //配置前置通知
    // 主要获取访问时间、访问的类、访问的方法

    @Before("execution(* com.itheima.ssm..controller.*.*(..))&&!execution(* com.itheima.ssm.controller.SysLogController.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();//获得访问时间
        clazz = jp.getTarget().getClass();//访问的类
        String methodName = jp.getSignature().getName();//获得访问方法名称
        //获得方法参数
        Object[] args = jp.getArgs();
        if (args.length == 0 || args == null) {
            method = clazz.getMethod(methodName);//获得类中的无参方法
        } else {
            //有参数，就将args中所有元素遍历，获取对应的Class,装入到一个Class[]
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName, classArgs);
        }

    }

    //配置最终通知
    // 主要获取日志中其它信息，时长、ip、url...
    @After("execution(* com.itheima.ssm..controller.*.*(..)))&&!execution(* com.itheima.ssm.controller.SysLogController.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        //获取时长
        Long executionTime = new Date().getTime() - visitTime.getTime();
        //获取ip
        String ip = request.getRemoteAddr();
        //获取url
        String url = "";
        //获取类上的RequestMapping注解
        if (clazz != null && method != null && clazz != LogAop.class) {
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classValue = classAnnotation.value();
                //获取方法上的注解
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];

                    //获取用户
                    SecurityContext context = SecurityContextHolder.getContext();
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();//获得用户名
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(executionTime);
                    sysLog.setUrl(url);
                    sysLog.setVisitTime(visitTime);
                    sysLog.setIp(ip);
                    sysLog.setUsername(username);
                    sysLog.setMethod("[类名]"+clazz.getName()+"[方法名]"+method.getName());

                    //调用service 调用dao将日志对象存进数据库
                    sysLogService.save(sysLog);
                }
            }
        }
    }
}
