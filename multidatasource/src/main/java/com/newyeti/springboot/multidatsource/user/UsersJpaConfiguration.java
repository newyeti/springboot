package com.newyeti.springboot.multidatasource.user;

import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackageClasses = User.class,
  entityManagerFactoryRef = "usersEntityManagerFactory",
  transactionManagerRef = "usersTransactionManager"
)
public class UsersJpaConfiguration {

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean usersEntityManagerFactory(
      @Qualifier("usersDataSource") DataSource usersDataSource,
      EntityManagerFactoryBuilder builder) {
        return builder
          .dataSource(usersDataSource)
          .packages(User.class)
          .build();
    }

    @Bean
    public PlatformTransactionManager usersTransactionManager(
        @Qualifier("usersEntityManagerFactory") LocalContainerEntityManagerFactoryBean usersEntityManagerFactory){
        return new JpaTransactionManager(
          Objects.requireNonNull(usersEntityManagerFactory.getObject())
        );
    }

}
