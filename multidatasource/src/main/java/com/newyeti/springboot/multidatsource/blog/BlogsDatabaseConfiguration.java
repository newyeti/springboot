package com.newyeti.springboot.multidatasource.blog;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class BlogsDatabaseConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.blogs")
    public DataSourceProperties blogsDataSourceProperties() {
      return new DataSourceProperties();
    }

    @Bean
    public DataSource blogsDataSource() {
      return blogsDataSourceProperties()
        .initializeDataSourceBuilder()
        .build();
    }

    @Bean
    public JdbcTemplate blogsJdbcTemplate(@Qualifier("blogsDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    
}
