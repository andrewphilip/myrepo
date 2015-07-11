package andy.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class StringEncryptAfterReturning implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnVal, Method method, Object[] args,
			Object target) throws Throwable {

		System.out.println("AOP in AfterReturningAdvice :: and the refturn value is "+returnVal);	
	}

}
