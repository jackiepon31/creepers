package com.fosun.fc.projects.creepers.aop;

import org.apache.commons.lang3.StringEscapeUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Page;

@Component
@Aspect
public class ProcessorAspect implements ThrowsAdvice, Ordered {

    private static Logger logger = LoggerFactory.getLogger(ProcessorAspect.class);

    public ProcessorAspect() {
        logger.info(".....inital AOP ServiceDispatcherAspect......");
    }

    @Before(value = "execution(* com.fosun.fc.projects.diplomat.service.impl.ServiceDispatcherImpl..*(..))")
    public void doBefore(JoinPoint joinPoint) {
        // Page page = (Page) joinPoint.getArgs()[0];
        // String content = page.getHtml().toString();
        // content =
        // StringEscapeUtils.escapeHtml4(StringEscapeUtils.escapeJava(content));
        // page.setRawText(content);
        // joinPoint.getArgs()[0] = page;
        // logger.info("Before增强：目标方法的参数为：" +
        // Arrays.toString(joinPoint.getArgs()));
        // logger.info("joinPoint.getArgs()[0]:"+joinPoint.getArgs()[0]);
        // logger.info("Before增强：目标方法的参数为：" +
        // Arrays.toString(joinPoint.getArgs()));
        // logger.info("Before增强：被织入增强处理的目标对象为：" + joinPoint.getTarget());
    }

    @After(value = "execution(* com.fosun.fc.projects.diplomat.service.impl.ServiceDispatcherImpl..*(..))")
    public void doAfter() {
        logger.info("do after inform.....");
    }

    @SuppressWarnings("deprecation")
    @Around(value = "execution(* com.fosun.fc.projects.creepers.pageprocessor.*.process*(..))")
    public void doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("entry  Around 方法处理page参数.");
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0 && args[0].getClass() == Page.class) {
            Page page = (Page) args[0];
            String content = page.getHtml().toString();
            content = StringEscapeUtils.unescapeHtml4(StringEscapeUtils.unescapeJava(content)).replaceAll("\"\"", "\"");
            page.setHtml(null);
            page.setRawText(content);
            args[0] = page;
        }
        logger.info("执行proceed方法.");
        joinPoint.proceed();
        logger.info("exit  Around 方法处理page参数.");
    }

    @AfterReturning(
            value = "execution(* com.fosun.fc.projects.diplomat.service.impl.ServiceDispatcherImpl*.dispatch*(..))",
            returning = "retVal")
    public void doAfterReturning(JoinPoint jp, String retVal) {
        logger.info("do After Returning inform and get param....:" + retVal);
    }

    @AfterThrowing(
            value = "execution(* com.fosun.fc.projects.diplomat.service.impl.ServiceDispatcherImpl*.dispatch*(..))",
            throwing = "ex")
    public void afterThrowing(JoinPoint jp, Throwable ex) {
    }

    /*
     * @see org.springframework.core.Ordered#getOrder()
     */
    @Override
    public int getOrder() {
        return 1;
    }

}
