package com.customerTracker.web_customer_tracker_MAVEN.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import com.mchange.v2.c3p0.ComboPooledDataSource;
//import com.mysql.cj.xdevapi.SessionFactory;

@Configuration //No XML configuration
@ComponentScan(basePackages="com.customerTracker")//Scan all the object into all packages.
@EnableWebMvc //@EnableWebMvc is equivalent to <mvc:annotation-driven />.
public class MvcConfiguration implements WebMvcConfigurer {

	@Bean //Define Spring MVC view resolver
	public ViewResolver getViewResolver(){
		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		return resolver;
		
	}//Close getViewResolver method.
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		
	}//Close addResourceHandlers method.
	
	@Bean(destroyMethod="close")
	public ComboPooledDataSource myDataSource() {
		
		ComboPooledDataSource myDataSource = new ComboPooledDataSource(); 
		
		try {
			
			myDataSource.setDriverClass("com.mysql.jdbc.Driver");
			myDataSource.setJdbcUrl("jdbc:mysql://192.168.99.100:3306/web_customer_tracker?useSSL=false");
			myDataSource.setUser("springstudent");
			myDataSource.setPassword("springstudent");
			
			myDataSource.setInitialPoolSize(5);
			myDataSource.setMinPoolSize(5);
			myDataSource.setMaxPoolSize(20);
			myDataSource.setMaxIdleTime(30000);
			
		}//Close try.
		
		catch (PropertyVetoException e) {
			e.printStackTrace();
		}//Close catch.
		
		return myDataSource;
		
	}//close myData
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		DataSource dataSource = myDataSource();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan(new String[] { "com.customerTracker" });
		
		Properties pro = new Properties();
		pro.put("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
		pro.put("hibernate.show_sql","true");
		
		sessionFactory.setHibernateProperties(pro);
		
		return sessionFactory;
		
	}//Close sessionFactory method.
	
	@Bean
	public void myTransactionManager() {
		
		HibernateTransactionManager transactionManager=new HibernateTransactionManager();
		LocalSessionFactoryBean mySessionFactory=sessionFactory();
		
		//transactionManager.setSessionFactory(mySessionFactory);
		
	}//Close myTransactionManager method.
	/*
	     <!-- Step 3: Setup Hibernate transaction manager -->
	<bean id="myTransactionManager"
            class="org.springframework.orm.hibernate5.HibernateTransactionManager">
        <property name="sessionFactory" ref="sessionFactory"/>
    </bean>
	 */
	
	
	
	
	
	
	
	
	
}//Close MvcConfiguration
