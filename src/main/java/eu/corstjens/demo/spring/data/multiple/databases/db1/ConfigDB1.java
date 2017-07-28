package eu.corstjens.demo.spring.data.multiple.databases.db1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by koencorstjens on 13/07/17.
 */
@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "db1LocalContainerEntityManagerFactoryBean",
        transactionManagerRef = "db1TransactionManager")
public class ConfigDB1 {

    @Bean("db1LocalContainerEntityManagerFactoryBean")
    @Autowired
    public LocalContainerEntityManagerFactoryBean db1LocalContainerEntityManagerFactoryBean(@Qualifier("db1DataSource") DataSource db1DataSource) {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(db1DataSource);
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setPackagesToScan("eu.corstjens.demo.spring.data.multiple.databases.db1");

        return factoryBean;
    }


    @Bean(name = "db1TransactionManager")
    @Autowired
    public PlatformTransactionManager db1TransactionManager(@Qualifier("db1LocalContainerEntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean db1EntityManagerFactory) {
        return new JpaTransactionManager(db1EntityManagerFactory.getObject());
    }


    @Bean(name = "db1DataSourceProperties")
    @Primary
    @ConfigurationProperties("demo.corstjens.db1")
    public DataSourceProperties db1DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "db1DataSource")
    @Primary
    public DataSource db1DataSource() {
        return db1DataSourceProperties().initializeDataSourceBuilder().build();
    }


}
