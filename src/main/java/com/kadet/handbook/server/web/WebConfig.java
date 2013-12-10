package com.kadet.handbook.server.web;

import com.kadet.handbook.server.entity.Chapter;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Properties;

@Configuration // This is a Spring config class
@EnableTransactionManagement // As <tx:annotation-config />
@EnableWebMvc  // As <mvc:component-scan .. />
@ComponentScan(basePackages = "com.kadet.handbook.server") // As <context:component-scan ../>
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    // Injectible Transaction Manager Bean
    public HibernateTransactionManager createTransactionManager() {

        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(
                createSessionFactoryBean().getObject());
        return transactionManager;
    }

    @Bean
    // Injectible Hibernate SessionFactory Bean
    public LocalSessionFactoryBean createSessionFactoryBean() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.show_sql", "true");
        localSessionFactoryBean.setHibernateProperties(properties);
        localSessionFactoryBean.setAnnotatedClasses(new Class<?>[]{Chapter.class});
        localSessionFactoryBean.setDataSource(createDataSource());
        return localSessionFactoryBean;
    }

    @Bean
    // Injectible DataSource Bean
    public BasicDataSource createDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/fxdb");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return dataSource;
    }

}
