package andy.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class StringEncryptAround implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation mInv) throws Throwable {
		Object obj=mInv.proceed();
		System.out.println("Inside invoke()::"+obj.toString());
		return obj;
	}

}
