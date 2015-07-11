package andy.webjpa.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class DataJPASimpleInitializer implements WebApplicationInitializer {

	private static final String DISPATCHER_SERVLET_NAME="dispatcher";
	private static final String DISPATCHER_SERVLET_MAPPING="/";
	
	public void onStartup(ServletContext sCtx) throws ServletException {

//		AnnotationConfigWebApplicationContext rootCtx=new AnnotationConfigWebApplicationContext();
//		rootCtx.register(ApplicationContext.class);
//		ServletRegistration.Dynamic dispatcher=sCtx.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(rootCtx));
//		dispatcher.setLoadOnStartup(1);
//		dispatcher.addMapping(DISPATCHER_SERVLET_MAPPING);
//		
//		sCtx.addListener(new ContextLoaderListener(rootCtx));
		AnnotationConfigWebApplicationContext ctxt=getContext();
		ctxt.setServletContext(sCtx);
		sCtx.addListener(new ContextLoaderListener(ctxt));
		
		ServletRegistration.Dynamic dispatcher= sCtx.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(ctxt));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping(DISPATCHER_SERVLET_MAPPING);
	}

	private AnnotationConfigWebApplicationContext getContext(){
		AnnotationConfigWebApplicationContext ctx=new AnnotationConfigWebApplicationContext();
		//ctx.setConfigLocation("andy.webjpa.config");
		ctx.register(ApplicationContext.class);
		
		return ctx;
	}


}
