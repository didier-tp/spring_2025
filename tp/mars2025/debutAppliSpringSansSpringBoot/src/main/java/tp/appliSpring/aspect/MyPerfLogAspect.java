package tp.appliSpring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Profile("perf")
public class MyPerfLogAspect {

	@Pointcut("execution(* tp.appliSpring.exemple.*.*(..))")
	public void surPackageExemple() {}
	
	@Pointcut("execution(* tp.appliSpring.service.*.*(..))")
	public void surPackageService() {}
	
	//@within pour annotation @Aff (avec @Target(ElementType.TYPE) placée sur l'ensemble d'une classe 
	@Pointcut("@within(tp.appliSpring.annotation.Aff)")
	public void annotAffPointcut(){ 
	} 
			
	//@within pour annotation @LogExecutionTime (avec @Target(ElementType.METHOD) placée sur une méthode 
	@Pointcut("@annotation(tp.appliSpring.annotation.LogExecutionTime)")
	public void annotLogExecutionTimePointcut(){ 
	}
	
	//@Around("annotAffPointcut()")
	//@Around("surPackageExemple() && annotAffPointcut()")
	//@Around("annotLogExecutionTimePointcut()")
	//@Around("surPackageExemple() || surPackageService()")
	@Around("(surPackageExemple() || surPackageService()) && annotLogExecutionTimePointcut()")
	public Object doPerfLog(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("<< trace == debut == " + pjp.getSignature().toLongString() + " <<");
		/*for(Object a : pjp.getArgs()) { System.out.print(" arg:" + a ); }
		    System.out.print("\n");*/
		long td = System.nanoTime();
		Object objRes = pjp.proceed();
		long tf = System.nanoTime();
		System.out.println(
				">> trace == fin == " + pjp.getSignature().toShortString() + " [" + (tf - td) / 1000000.0 + " ms] >>");
		return objRes;
	}
	
}
