package eu.corstjens.demo.spring.data.multiple.databases.db1;


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
@EnableJpaRepositories(entityManagerFactoryRef = "db1EntityManagerFactory",
        transactionManagerRef = "db1TransactionManager")
public class ConfigDB1 {

    @Bean
    public PlatformTransactionManager db1TransactionManager() {
        return new JpaTransactionManager(db1EntityManagerFactory().getObject());
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean db1EntityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(db1DataSource());
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setPackagesToScan("eu.corstjens.demo.spring.data.multiple.databases.db1");

        return factoryBean;
    }

    @Bean
    @Primary
    @ConfigurationProperties("demo.corstjens.db1")
    public DataSourceProperties db1DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public javax.sql.DataSource db1DataSource() {
        return db1DataSourceProperties().initializeDataSourceBuilder().build();
    }


}
