package com.newyeti.springboot.multidatasource.blog;
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackageClasses = Blog.class,
  entityManagerFactoryRef = "blogsEntityManagerFactory",
  transactionManagerRef = "blogsTransactionManager"
)
public class BlogsJpaConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean blogsEntityManagerFactory(
      @Qualifier("blogsDataSource") DataSource blogsDataSource,
      EntityManagerFactoryBuilder builder) {
        return builder
          .dataSource(blogsDataSource)
          .packages(Blog.class)
          .build();
    }

    @Bean
    public PlatformTransactionManager blogsTransactionManager(
        @Qualifier("blogsEntityManagerFactory") LocalContainerEntityManagerFactoryBean blogsEntityManagerFactory){
        return new JpaTransactionManager(
          Objects.requireNonNull(blogsEntityManagerFactory.getObject())
        );
    }

}
