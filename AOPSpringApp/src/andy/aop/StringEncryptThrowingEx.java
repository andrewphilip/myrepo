package andy.aop;

import org.springframework.aop.ThrowsAdvice;

public class StringEncryptThrowingEx implements ThrowsAdvice {

	public void afterThrowing(IllegalArgumentException e) throws Throwable{
		System.out.println("AOP in ThrowsAdvice :: executed when method throws exception.");
	}
}
