package kz.iitu.itse1909.amirlan.aop.AnnotationMatchingPointcutDemo;


public class Guitarist {
    public void sing() {
        System.out.println("Dream of ways to throw it all away");
    }

    @AdviceRequired
    public void sing(Guitar guitar) {
        System.out.println("play: " + guitar.play());
    }

    public void rest(){
        System.out.println("zzz");
    }
}
