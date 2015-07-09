package andy.webjpa.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.ejb.HibernatePersistence;
//import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import andy.webjpa.model.Method;

import com.jolbox.bonecp.BoneCPDataSource;
import javax.annotation.*;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ImportResource("classpath:applicationContext.xml")
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages={"andy.webjpa.controller",
							 "andy.webjpa.service"
							})
@EnableJpaRepositories(basePackages={"andy.webjpa.repository"})
public class ApplicationContext  extends WebMvcConfigurerAdapter{


//	private static final String DB_DRIVER="db.driver";
//	private static final String DB_PWD="db.password";
//	private static final String DB_URL="db.url";
//	private static final String DB_USER="db.username";
	private static final String HIBERNATE_DIALECT="hibernate.dialect";
//	private static final String ENTITYMGR_PKGS_TO_SCAN="entitymanager.packages.to.scan";
	
//	@ Resource
//	private Environment environment;
	
	@Bean
	public DataSource dataSource(){
		//BoneCPDataSource ds= new BoneCPDataSource();
		DriverManagerDataSource ds=new DriverManagerDataSource();
//		ds.setDriverClass(environment.getRequiredProperty(DB_DRIVER));
//		ds.setJdbcUrl(environment.getRequiredProperty(DB_URL));
//		ds.setUsername(environment.getRequiredProperty(DB_USER));
//		ds.setPassword(environment.getRequiredProperty(DB_PWD));
		System.err.println("Inside dataSource()");
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		//ds.setUrl("jdbc:oracle:thin:@norway.states.bls.gov:1521:lausoned");
		ds.setUrl("jdbc:oracle:thin:@panama.states.bls.gov:1521:lausonet");
		//ds.setUsername("laus15dev");
		ds.setUsername("lausacctest");
		ds.setPassword("Welcome1$");
		return ds;
	}
	
	@Bean(name = "entityManagerFactory")
	public EntityManagerFactory entityManagerFactory() throws Exception{
		LocalContainerEntityManagerFactoryBean fbean=new LocalContainerEntityManagerFactoryBean();
		fbean.setDataSource(dataSource());
		System.out.println(fbean.getDataSource().getConnection().getMetaData().getURL());
		System.err.println("Inside entityMgrFactoryBean()");

		//bean.setPackagesToScan(environment.getRequiredProperty(ENTITYMGR_PKGS_TO_SCAN));
		fbean.setPackagesToScan("andy.webjpa.model");
		System.out.println("After setting packages to scan.");
		
		Properties props=new Properties();
//		props.put(HIBERNATE_DIALECT, environment.getRequiredProperty(HIBERNATE_DIALECT));
//		props.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		props.put(HIBERNATE_DIALECT, "org.hibernate.dialect.Oracle10gDialect");
		props.put("hibernate.show_sql", "true");
		fbean.setJpaProperties(props);
		System.out.println("After setting jpa properties.");

		//bean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		HibernateJpaVendorAdapter vAdapter=new HibernateJpaVendorAdapter();
		fbean.setJpaVendorAdapter(vAdapter);
		fbean.afterPropertiesSet();
		return fbean.getObject();
	}
	
	@Bean
	public JpaTransactionManager transactionManager() throws Exception{
		System.out.println("Inside transactionManager()");
		JpaTransactionManager transMgr=new JpaTransactionManager();
		transMgr.setEntityManagerFactory(entityManagerFactory());
		System.out.println("transaction manager is "+ transMgr == null ? "null" : " not null.");
		return transMgr;
	}
	
	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver vResolver=new InternalResourceViewResolver();
		vResolver.setViewClass(JstlView.class);
		vResolver.setPrefix("/");
		vResolver.setSuffix(".jsp");
		return vResolver;
	}
	
}
