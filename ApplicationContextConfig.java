package com.niit.colloboration.config;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.colloboration.model.Blog;
import com.niit.colloboration.model.Event;
import com.niit.colloboration.model.Forum;
import com.niit.colloboration.model.Forum_comment;
import com.niit.colloboration.model.Friend;
import com.niit.colloboration.model.Job;
import com.niit.colloboration.model.Job_application;
import com.niit.colloboration.model.User;
@Configuration
@ComponentScan("com.niit.colloboration")
@EnableTransactionManagement
public class ApplicationContextConfig {

	@Bean(name="dataSource")
	public DataSource getH2DataSource()
	{
	DriverManagerDataSource datasource=new DriverManagerDataSource();
	datasource.setDriverClassName("org.h2.Driver");
	datasource.setUrl("jdbc:h2:tcp://localhost/~/collaborate");
	
    datasource.setUsername("sa");
    datasource.setPassword("");
    return datasource;
	}
    
    /*connectionProperties.setProperty("hibernate.hbm2ddl.auto","update");
    connectionProperties.setProperty("hibernate.show_sql", "true");
    connectionProperties.setProperty("hibernte.dialect", "org.hibernate.dialect.Oracle10gDialect");
    connectionProperties.setProperty("hiberanate.formate_sql", "true");
    connectionProperties.setProperty("hibernate.jdbc.use_get_generated_keys", "true");
    //connectionProperties.setProperty("hibernate.default_schema", "system"); 		
    datasource.setConnectionProperties(connectionProperties);
    return datasource;
@SuppressWarnings("unused")*/
	private Properties getHibernateProperties(){
		Properties properties=new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;
	}
	
	
   @Autowired
   @Bean(name="sessionFactory")
   public SessionFactory getSessionFactory(DataSource dataSource){
   LocalSessionFactoryBuilder sessionBuilder=new LocalSessionFactoryBuilder(getH2DataSource());
	sessionBuilder.addProperties(getHibernateProperties());
		//sessionBuilder.addAnnotatedClass(Forum.class);
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(Friend.class);
		sessionBuilder.addAnnotatedClass(Job.class);
		sessionBuilder.addAnnotatedClass(Job_application.class);
		sessionBuilder.addAnnotatedClass(User.class);
		sessionBuilder.addAnnotatedClass(Event.class);
		//sessionBuilder.addAnnotatedClass(Forum_comment.class);
		
		System.out.println("Database connected");
		return sessionBuilder.buildSessionFactory();
	
	}
   @Autowired
   @Bean(name="transactionManager")
   public HibernateTransactionManager getTransactionManager(
   		SessionFactory sessionFactory){
   		HibernateTransactionManager transactionManager=new HibernateTransactionManager(sessionFactory);
   		return transactionManager;
   	
	
   }   
}

