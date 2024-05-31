package com.newyeti.springboot.multitenancy.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.util.Assert;

import lombok.Data;

@Data
public class MultiTenantDataSources {
  
  private Map<String, DataSource> dataSources = new HashMap<>();
  private String defaultTenantId;

  public MultiTenantDataSources(String defaultTenantId) {
    Assert.hasText(defaultTenantId, "Default Tenant Id is required");
    this.defaultTenantId = defaultTenantId;
  }

  public DataSource get(String tenant) {
    return dataSources.get(tenant);
  }

  public DataSource getDeafult() {
    return dataSources.get(defaultTenantId);
  }

  public void add(String tenant, DataSource dataSource) {
    dataSources.put(tenant, dataSource);
  }

  public void remove(String tenant) {
    dataSources.remove(tenant);
  }

  public Collection<DataSource> getAll() {
    return dataSources.values(); 
  }

}
