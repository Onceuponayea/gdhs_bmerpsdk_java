package com.hrghs.xycb.aops;

import com.hrghs.xycb.domains.BanmaerpProperties;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Aspect
@Component
public class BanmaerpDefaultPropertiesAspect {
    private static final Logger logger = LoggerFactory.getLogger(BanmaerpDefaultPropertiesAspect.class);
    @Autowired
    ApplicationContext applicationContext;
    @Around("@annotation(com.hrghs.xycb.annotations.CheckBanmaerpProperties)")
    public Object checkBanmaerpProperties(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature= (MethodSignature) joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        Object[] newArgs = new Object[args.length];
        Class[] parameterTypes = methodSignature.getParameterTypes();
        String defaultBmerpPropertyBeanName = "erp.banmaerp-com.hrghs.xycb.domains.BanmaerpProperties";
        String defaultBmerpPropertyBeanName2 = "banmaerpProperties";
        Map<String,BanmaerpProperties> banmaerpPropertiesMap=applicationContext.getBeansOfType(BanmaerpProperties.class);
        BanmaerpProperties banmaerpProperties = banmaerpPropertiesMap.get(defaultBmerpPropertyBeanName);
        banmaerpProperties = banmaerpProperties==null?banmaerpPropertiesMap.get(defaultBmerpPropertyBeanName2):banmaerpProperties;
        int banmaerpProsArgsIndex = -1;
        for (int i=0;i< parameterTypes.length;i++){
            banmaerpProsArgsIndex= (parameterTypes[i] == BanmaerpProperties.class)?i:banmaerpProsArgsIndex;
        }
        if (args[banmaerpProsArgsIndex]==null){
            logger.info("Method: {} , Banmaerp App Properties Not specified, switching to platform appInfo",methodSignature.getName());
            if (banmaerpProsArgsIndex>-1){
                //说明该方法有BanmaerpProperties类型的参数
                Object objects = args[banmaerpProsArgsIndex];
                for (int m=0;m< newArgs.length;m++){
                    if (m==banmaerpProsArgsIndex){
                        if (objects==null && parameterTypes[m].getSimpleName().equalsIgnoreCase(BanmaerpProperties.class.getSimpleName())){
                            newArgs[m] = banmaerpProperties;
                        }
                    }else{
                        newArgs[m]=args[m];
                    }
                }
            }
            return joinPoint.proceed(newArgs);
        }
        //原路走
        return joinPoint.proceed(args);
    }
}
