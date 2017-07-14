package eu.corstjens.demo.spring.data.multiple.databases.db2;


import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Created by koencorstjens on 13/07/17.
 */
@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "db2EntityManagerFactory",
        transactionManagerRef = "db2TransactionManager")
public class ConfigDB2 {

    @Bean
    public PlatformTransactionManager db2TransactionManager() {
        return new JpaTransactionManager(db2EntityManagerFactory().getObject());
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(db2DataSource());
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setPackagesToScan("eu.corstjens.demo.spring.data.multiple.databases.db2");

        return factoryBean;
    }

    @Bean
    @ConfigurationProperties("demo.corstjens.db2")
    public DataSourceProperties db2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "dataSource2")
    public javax.sql.DataSource db2DataSource() {
        return db2DataSourceProperties().initializeDataSourceBuilder().build();
    }


}
