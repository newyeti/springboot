package com.newyeti.springboot.multidatasource.user;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class UsersDatabaseConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.users")
    public DataSourceProperties usersDataSourceProperties() {
      return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource usersDataSource(){
      return usersDataSourceProperties()
        .initializeDataSourceBuilder()
        .build();
    }

    @Bean
    public JdbcTemplate usersJdbcTemplate(@Qualifier("usersDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
