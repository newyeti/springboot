package com.newyeti.springboot.multitenancy.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Configuration
@Primary
@Data
@EqualsAndHashCode(callSuper=false)
public class TenantDataSourceProperties extends DataSourceProperties{
  private String tenantId;
}
