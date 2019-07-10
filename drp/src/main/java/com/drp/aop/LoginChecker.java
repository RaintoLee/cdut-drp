package com.drp.aop;

import com.drp.annotations.NoNeedLogin;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by Liu.Li on 2018/03/21.
 */

@Component
@Aspect
public class LoginChecker {


    /*
     * 登录的AOP会对所有的controller类中的方法进行判断，如果方法没有NoNeedLogin注解，则说明
     * 该方法是一个需要登录之后才可以访问的方法，目前只能对返回ModelAndView的方法进行判断，对
     * 结果类型是Json的，还没有做。需要在未来进行完善。
     */
    @Around(value = "execution(* com.drp.controller.*Controller*.*(..)) ")
    public Object arountController(ProceedingJoinPoint pjp) {
        //获取方法签名，modleMap等
        Signature s = pjp.getSignature();
        Object[] args = pjp.getArgs();
        ModelMap modelMap = null;

        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg.getClass().getName().equals("org.springframework.validation.support.BindingAwareModelMap")) {
                modelMap = (ModelMap) arg;
            }
        }

        String methodReturnType = s.toLongString().split(" ")[1];

        String methodName = pjp.getSignature().getName();
        Object target = pjp.getTarget();
        Class[] parameterTypes = ((MethodSignature) pjp.getSignature()).getMethod().getParameterTypes();

        Method method = null;
        try {
            method = target.getClass().getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        HttpServletRequest request = null;
        HttpServletResponse response = null;

        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        NoNeedLogin noNeedLogin = method.getAnnotation(NoNeedLogin.class);

        if (noNeedLogin == null) {
            //该方法需要判断是否登录
            boolean logedIn = false;
            if (null != request.getSession().getAttribute("accountId") && !"".equals(request.getSession().getAttribute("accountId").toString().trim())) {
                logedIn = true;//用户已经登录
            }
            if (methodReturnType.equals("org.springframework.web.servlet.ModelAndView") && logedIn == false) {
                //该方法是返回的Spring ModelAndView对象，并且用户没有登录，需要让用户跳转到登录界面
                ModelAndView mav = new ModelAndView("login", modelMap);
                return mav;
            }
            //这里还要判断如果方法是返回的其他对象，比如JsonObject或者JsonArray，如何处理
        } else {
            //该方法不用登录登录
        }
        Object retVal = null;
        try {
            retVal = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return retVal;
    }
}
