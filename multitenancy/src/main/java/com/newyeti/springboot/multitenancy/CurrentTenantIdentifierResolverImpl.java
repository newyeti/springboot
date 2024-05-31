package com.newyeti.springboot.multitenancy;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver<String>{

  private static final String DEFAULT_DB = "DS1";

  @Override
  public String resolveCurrentTenantIdentifier() {
    if(!StringUtils.hasLength(AppTenantContext.getCurrentTenant())){
            return DEFAULT_DB;
    } else{
        return AppTenantContext.getCurrentTenant();
    }
  }

  @Override
  public boolean validateExistingCurrentSessions() {
   return true;
  }
  
}
