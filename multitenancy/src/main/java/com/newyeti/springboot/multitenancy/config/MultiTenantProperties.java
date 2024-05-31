package com.newyeti.springboot.multitenancy.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;


@ConfigurationProperties(prefix = "spring.tenant")
@Data
public class MultiTenantProperties {

    @Value("${spring.tenant.default-tenant-id}")
    private String defaultTenantId;

    private List<TenantDataSourceProperties> dataSources;

}
