package kz.iitu.itse1909.amirlan.aop.AnnotationMatchingPointcutDemo;

import kz.iitu.itse1909.amirlan.aop.AspectJExpressionPointcutDemo.SimpleAdvice;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

public class AnnotationPointcutDemo {
    public static void main(String... args) {
        Guitarist johnMayer = new Guitarist();
        AnnotationMatchingPointcut pc = AnnotationMatchingPointcut
                .forMethodAnnotation(AdviceRequired.class);
        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleAdvice());
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(johnMayer);
        pf.addAdvisor(advisor);
        Guitarist proxy = (Guitarist) pf.getProxy();
        proxy.sing(new Guitar());
        proxy.rest();
    }
}


