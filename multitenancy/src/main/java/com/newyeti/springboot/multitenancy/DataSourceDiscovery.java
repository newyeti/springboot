package com.newyeti.springboot.multitenancy;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.newyeti.springboot.multitenancy.config.MultiTenantDataSources;

@Component
public class DataSourceDiscovery {
  
   private MultiTenantDataSources multiTenantDataSource;

   public DataSourceDiscovery(MultiTenantDataSources multiTenantDataSource) {
    this.multiTenantDataSource = multiTenantDataSource;
   }
   
   public DataSource getDataSource(String tenant) {
      return this.multiTenantDataSource.get(tenant);
   }

   public DataSource getDefaultDataSource(){
      return this.multiTenantDataSource.getDeafult();
   }
}
