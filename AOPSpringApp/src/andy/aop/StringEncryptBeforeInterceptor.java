package andy.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class StringEncryptBeforeInterceptor implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] arg1, Object target)
			throws Throwable {
		System.out.println("AOP: beforeMethod invocation:");
	}

}
