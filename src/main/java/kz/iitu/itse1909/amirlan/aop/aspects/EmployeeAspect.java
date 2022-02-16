package kz.iitu.itse1909.amirlan.aop.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class EmployeeAspect {

    @Pointcut("execution(* Employee.getName())")
    public void getNameAdvice(){
        System.out.println("Executing Advice on getName()");
    }

    @Before("execution(* EmployeeService.get*())")
    public void getAllAdvice(){
        System.out.println("Service method getter called");
    }
}
