package kz.iitu.itse1909.amirlan.kernel.aspect;

import kz.iitu.itse1909.amirlan.application.user.service.impl.AppUserDetailsService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(AppUserDetailsService.class);

    @Pointcut("execution(* kz.iitu.itse1909.amirlan.application.user.controller.UserController.*(..))")
    public void logPointcut() {}

    @Before("logPointcut()")
    public void logUserAction(JoinPoint joinPoint) {
        logger.info("Proceed " + joinPoint.getSignature().getDeclaringTypeName() +
                "." + joinPoint.getSignature().getName() +
                " with arguments: " + joinPoint.getArgs());
    }

    @After("logPointcut()")
    public void logExecutionFinished(JoinPoint joinPoint) {
        logger.info("Finished " + joinPoint.getSignature().getDeclaringTypeName() +
                "." + joinPoint.getSignature().getName());
    }

    @AfterReturning("logPointcut()")
    public void logSuccessReturn(JoinPoint joinPoint) {
        logger.info("Successfully processed " + joinPoint.getSignature().getDeclaringTypeName() +
                "." + joinPoint.getSignature().getName());
    }

    @AfterThrowing("logPointcut()")
    public void logThrowing(JoinPoint joinPoint) {
        logger.warn("Exception threw by " + joinPoint.getSignature().getDeclaringTypeName() +
                "." + joinPoint.getSignature().getName());
    }
}
