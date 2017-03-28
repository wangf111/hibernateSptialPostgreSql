package org.jug.algeria.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by wangf on 2017/3/23.
 */
@Configuration
public class PostgreSqlConfig {
  @Autowired
  private Environment env;

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getProperty("spring1.database.driverClassName"));
    dataSource.setUrl(env.getProperty("spring1.datasource.url"));
    dataSource.setUsername(env.getProperty("spring1.datasource.username"));
    dataSource.setPassword(env.getProperty("spring1.datasource.password"));
    return dataSource;
  }
}
