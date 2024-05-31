package com.newyeti.springboot.multitenancy.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.newyeti.springboot.multitenancy.Constants;

@Configuration
@EnableConfigurationProperties({MultiTenantProperties.class})
public class DataSourceConfig {

  private MultiTenantProperties multitenantProperties;

  public DataSourceConfig(MultiTenantProperties multitenantProperties) {
      this.multitenantProperties = multitenantProperties;
  }

  @Bean
  public DataSource dsOneDataSourceBuilder() {
    for(TenantDataSourceProperties  dataSourceProperties: multitenantProperties.getDataSources()) {
        if (dataSourceProperties.getTenantId().equals(Constants.DATASOURCE_ONE)) {
            return dataSourceProperties.initializeDataSourceBuilder().build();
        }
    }
    return null;
  }

  @Bean
  public DataSource dsTwoDataSourceBuilder() {
    for (TenantDataSourceProperties dataSourceProperties: multitenantProperties.getDataSources()) {
      if (dataSourceProperties.getTenantId().equals(Constants.DATASOURCE_TWO)){
        return dataSourceProperties.initializeDataSourceBuilder().build();
      }
    }
    return null;
  }

  @Bean(name = "dataSourceMap")
   @Primary
   public MultiTenantDataSources dataSourceInitialiser(){
      // The MultiTenantDataSources class stores the default tenant-id and Map<String, DataSource> for each tenant-ID.
      MultiTenantDataSources multiTenantDataSources = new  MultiTenantDataSources(multitenantProperties.getDefaultTenantId());
      multiTenantDataSources.add(Constants.DATASOURCE_ONE, dsOneDataSourceBuilder());
      multiTenantDataSources.add(Constants.DATASOURCE_TWO, dsTwoDataSourceBuilder());

      return multiTenantDataSources;
   }

  
}
