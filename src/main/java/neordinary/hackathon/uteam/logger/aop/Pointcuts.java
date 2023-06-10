package neordinary.hackathon.uteam.logger.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* neordinary.hackathon.uteam.controller..*(..))")
    public void controllerPoint(){}

    @Pointcut("execution(* neordinary.hackathon.uteam.service..*(..))")
    public void servicePoint(){}

    @Pointcut("execution(* neordinary.hackathon.uteam.repository..*(..))")
    public void repositoryPoint(){}
}
