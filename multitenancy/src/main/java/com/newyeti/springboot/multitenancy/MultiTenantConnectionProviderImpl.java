package com.newyeti.springboot.multitenancy;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.stereotype.Component;

@Component
public class MultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl<String>{

  private DataSourceDiscovery dataSourceDiscovery;

  public MultiTenantConnectionProviderImpl(DataSourceDiscovery dataSourceDiscovery) {
    this.dataSourceDiscovery = dataSourceDiscovery;
  }

  @Override
  protected DataSource selectAnyDataSource() {
    return this.dataSourceDiscovery.getDefaultDataSource();
  }

  @Override
  protected DataSource selectDataSource(String tenantIdentifier) {
    return this.dataSourceDiscovery.getDataSource(tenantIdentifier);
  }
  
}
