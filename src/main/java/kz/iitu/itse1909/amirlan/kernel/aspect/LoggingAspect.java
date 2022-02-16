package kz.iitu.itse1909.amirlan.kernel.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @AfterReturning("execution(* kz.iitu.itse1909.amirlan.application.user.controller.UserController.getUser(*))")
    public void getUserExecution() {
        System.out.println("yo info from aspect");
    }
}
