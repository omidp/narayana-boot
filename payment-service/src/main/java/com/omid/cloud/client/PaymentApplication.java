package com.omid.cloud.client;

import java.util.Arrays;
import java.util.Properties;

import javax.sql.DataSource;
import javax.sql.XADataSource;
import javax.transaction.TransactionManager;
import javax.transaction.Transactional;

import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.managed.DataSourceXAConnectionFactory;
import org.apache.commons.dbcp2.managed.ManagedDataSource;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.postgresql.xa.PGXADataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.jta.narayana.DbcpXADataSourceWrapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionManagerImple;
import com.omid.cloud.client.dao.PaymentDao;
import com.omid.cloud.client.model.PaymentEntity;

@SpringBootApplication
@Import(DbcpXADataSourceWrapper.class)
public class PaymentApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(PaymentApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder)
    {
        return builder.build();
    }

    @Bean
    CommandLineRunner cmd(ApplicationContext ctx)
    {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames)
            {
                // System.out.println(beanName);
            }
        };

    }
    
    
    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
    
    
    

}

@RestController
class ClientApi
{

    @Autowired
    RestTemplate restTemplate;

    
    @Autowired
    PaymentDao dao;

    @GetMapping("/pay")
    @Transactional(rollbackOn=RuntimeException.class)
    @org.springframework.transaction.annotation.Transactional(rollbackFor=RuntimeException.class)
    String getApi()
    {
        dao.save(new PaymentEntity(1000L, null));
        throw new RuntimeException("revert");
//        return "paid";
    }

}
