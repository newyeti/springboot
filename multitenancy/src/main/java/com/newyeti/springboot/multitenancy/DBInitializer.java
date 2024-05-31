package com.newyeti.springboot.multitenancy;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.newyeti.springboot.multitenancy.config.MultiTenantProperties;
import com.newyeti.springboot.multitenancy.config.TenantDataSourceProperties;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DBInitializer implements CommandLineRunner{

  private MultiTenantProperties multiTenantProperties;

  public DBInitializer(MultiTenantProperties properties) {
    this.multiTenantProperties = properties;
  }
  
  @Override
  public void run(String... args) throws Exception {
    
    for (TenantDataSourceProperties dataSourceProperties: multiTenantProperties.getDataSources()) {
      String dbUrl = dataSourceProperties.getUrl();
      String dbName = dbUrl.substring(dbUrl.lastIndexOf("/") + 1);
      String port = dbUrl.substring(dbUrl.lastIndexOf(":")+1, dbUrl.lastIndexOf("/"));
      
      DataSource dataSource =  DataSourceBuilder
      .create()
      .url(dataSourceProperties.getUrl().replace(dbName, "postgres"))
      .username(dataSourceProperties.getUsername())
      .password(dataSourceProperties.getPassword())
      .driverClassName(dataSourceProperties.getDriverClassName())
      .build();
      JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

      String sql = "SELECT 1 FROM pg_database WHERE datname = ?";
      Boolean dbExists = jdbcTemplate.queryForObject(sql, Boolean.class, new Object[]{dbName});

      if (Boolean.FALSE.equals(dbExists)) {
        String createDbSql = "CREATE DATABASE " + dbName;
            jdbcTemplate.execute(createDbSql);
        log.info("Database created: " + dbName +  " at port: " + port);
      } else {
        log.info("Database already exist: " + dbName  + " at port: " + port);
      }

    }
  }
  
}
