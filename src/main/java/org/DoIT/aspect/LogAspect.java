package org.DoIT.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component
public class LogAspect {

    public void beforeServiceMethodInvocation(JoinPoint jp) {
        System.out.println("Invocation of method " + jp.getSignature());
    }

    public Object aroundServiceMethodExecution(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = point.proceed();
        long end = System.currentTimeMillis();
        System.out.println("Execution time of method " + point.getSignature() + " : " + (end - start) + "msec.");
        return proceed;
    }
}
