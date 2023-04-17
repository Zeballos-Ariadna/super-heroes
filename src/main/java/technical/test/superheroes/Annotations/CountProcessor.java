package technical.test.superheroes.Annotations;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CountProcessor {

    @Around("@annotation(technical.test.superheroes.Annotations.Count)")
    public Object countExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        Object run = proceedingJoinPoint.proceed();
        long excecutionTime = System.currentTimeMillis() - start;
        System.out.println(proceedingJoinPoint.getSignature() + " was executed in " + excecutionTime + " ms.");
        return run;
    }
}
