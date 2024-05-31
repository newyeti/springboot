package com.newyeti.springboot.multitenancy;

import java.io.IOException;
import java.util.Objects;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AppTenantContext implements Filter{

  private static final String LOGGER_TENANT_ID = "tenant_id";
  public static final String PRIVATE_TENANT_HEADER = "X-PrivateTenant";
  private static final String DEFAULT_TENANT = Constants.DATASOURCE_ONE;
  private static final ThreadLocal<String> currentTenant = new InheritableThreadLocal<>();

  public static String getCurrentTenant() {
    String tenant = currentTenant.get();
    return Objects.requireNonNullElse(tenant, DEFAULT_TENANT);
  }

  public static void setCurrentTenant(String tenantId) {
    log.info("{1} : {2}", LOGGER_TENANT_ID, tenantId);
    currentTenant.set(tenantId);
  }

  public static void clearTenant() {
    log.info("Tenant {1} removed", currentTenant.get());
    currentTenant.remove();
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
      HttpServletRequest req = (HttpServletRequest) request;
      HttpServletResponse res = (HttpServletResponse) response;
      String privateTenant = req.getHeader(PRIVATE_TENANT_HEADER);
      if (privateTenant != null) {
          AppTenantContext.setCurrentTenant(privateTenant.toUpperCase());
      }
      chain.doFilter(request, response);
  }
  
}
