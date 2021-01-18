package pl.javastart.config;

import net.bytebuddy.asm.Advice;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JpaConfig {
    // WCZESNIEJSZA WERSJA

//    @Bean
//    public LocalContainerEntityManagerFactoryBean createEMF(JpaVendorAdapter jpaVendorAdapter) {
//        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean =
//                new LocalContainerEntityManagerFactoryBean();
//        Map<String, String> properties = new HashMap<>();
//        properties.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC");
//        properties.put("javax.persistence.jdbc.user","root");
//        properties.put("javax.persistence.jdbc.password","root");
//        properties.put("javax.persistence.jdbc.driver","com.mysql.jdbc.Driver");
//        properties.put("javax.persistence.schema-generation.database.action","drop-and-create");
//
//        localContainerEntityManagerFactoryBean.setPersistenceUnitName("spring-jpa-pu");
//        localContainerEntityManagerFactoryBean.setJpaPropertyMap(properties);
//        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
//        localContainerEntityManagerFactoryBean.setPackagesToScan("pl.javastart.model");
//        return localContainerEntityManagerFactoryBean;
//    }

    @Bean
    public LocalContainerEntityManagerFactoryBean createEMF(JpaVendorAdapter jpaVendorAdapter, DataSource dataSource){
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean =
                new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setPersistenceUnitName("spring-jpa-pu");
        localContainerEntityManagerFactoryBean.setDataSource(dataSource);
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        localContainerEntityManagerFactoryBean.setPackagesToScan("pl.javastart.model");
        return localContainerEntityManagerFactoryBean;
    }

    @Bean
    public DataSource createDS(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("root");
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setInitialSize(5);
        return basicDataSource;
    }
    @Bean
    public JpaVendorAdapter createVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter =
                new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        hibernateJpaVendorAdapter.setShowSql(true);
        return hibernateJpaVendorAdapter;
    }
}