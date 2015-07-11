package andy.bean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import andy.bean.StringEncryption;


public class AopApp {

	public static void main(String[] args) {

			ConfigurableApplicationContext ctx=new ClassPathXmlApplicationContext(new String[]{"bean-context.xml"});
			StringEncryption aspect=(StringEncryption) ctx.getBean("encryptBeanProxy");
			String txt="testing123";
			System.out.println("Text:"+txt);
			aspect.encrypt2MD5(txt);
			//aspect.checkThrowException();
			ctx.close();
	}

}
