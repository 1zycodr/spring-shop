package kz.iitu.itse1909.amirlan.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;

@Aspect
public class EmployeeAspectJoinPoint {

    @Before("execution(* Employee.set*(*))")
    public void loggingAdvice(JoinPoint joinPoint){
        System.out.println("Before running loggingAdvice on method="+joinPoint.toString());

        System.out.println("Agruments Passed=" + Arrays.toString(joinPoint.getArgs()));

    }

    //Advice arguments, will be applied to bean methods with single String argument
    @Before("args(name)")
    public void logStringArguments(String name){
        System.out.println("String argument passed="+name);
    }
}
