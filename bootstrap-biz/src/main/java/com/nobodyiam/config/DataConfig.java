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

    /**
     * @return the h2 database data source
     */
    @Bean
    public DataSource h2DataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        try {
            dataSource.setDriverClass("org.h2.Driver");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setJdbcUrl("jdbc:h2:mem:bootstrap;INIT=runscript from 'classpath:sql/bootstrap.h2.sql'\\;" +
                "runscript from 'classpath:sql/bootstrap.insert.sql'");
        dataSource.setUser("sa");
        dataSource.setPassword("");
        dataSource.setAcquireIncrement(10);
        dataSource.setIdleConnectionTestPeriod(60);
        dataSource.setMaxPoolSize(30);
        dataSource.setMinPoolSize(5);
        dataSource.setMaxStatements(10);

        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(h2DataSource());
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(h2DataSource());
        sessionFactory.setTypeAliasesPackage("com.nobodyiam.dto");
        return sessionFactory;
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return transactionManager();
    }
}
