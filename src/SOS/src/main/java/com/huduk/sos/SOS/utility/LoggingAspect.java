package com.huduk.sos.SOS.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Log log = LogFactory.getLog(LoggingAspect.class);

    @AfterThrowing(pointcut = "execution(* com.huduk.sos.SOS.service.*Impl.*(..))", throwing = "exception")
    public void logServiceExpception(Exception exception) {
        log.error(exception.getMessage(), exception);
    }
    
}
