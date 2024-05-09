package at.ac.univie.imse.backend.configuration;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration(proxyBeanMethods = false)
public class DataSources {

    @Bean
    @Primary
    @ConfigurationProperties("dbs.maria.datasource")
    public DataSourceProperties mariaDbProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    DataSource mariaDb(DataSourceProperties mariaDbProperties){
        return mariaDbProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    @ConfigurationProperties("dbs.mongo.datasource")
    public  DataSourceProperties mongoDbProperties(){
        return new DataSourceProperties();
    }

    @Bean
    DataSource mongoDb(DataSourceProperties mongoDbProperties){
        return mongoDbProperties.initializeDataSourceBuilder().build();
    }
}
