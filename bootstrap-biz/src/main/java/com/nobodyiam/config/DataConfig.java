package com.nobodyiam.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Created by Jason on 7/5/15.
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.nobodyiam.mapper")
public class DataConfig implements TransactionManagementConfigurer {

    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
        } catch (PropertyVetoException e) {
            return null;
        }
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/bootstrap");
        dataSource.setUser("bootstrap");
        dataSource.setPassword("bootstrap");
        dataSource.setAcquireIncrement(10);
        dataSource.setIdleConnectionTestPeriod(60);
        dataSource.setMaxPoolSize(30);
        dataSource.setMinPoolSize(5);
        dataSource.setMaxStatements(10);

        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setTypeAliasesPackage("com.nobodyiam.dto");
        return sessionFactory;
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return transactionManager();
    }
}
