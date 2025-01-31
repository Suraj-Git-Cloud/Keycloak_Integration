package com.springredis.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
//@EnableTransactionManagement
public class DataSourceConfig {

	@Bean(name = "read_datasource")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		// Make sure jdbcUrl is properly set
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/savvis2_read"); // Correct JDBC URL
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}

	@Bean(name = "readJdbcTemplate")
	public JdbcTemplate jdbcTemplate(@Qualifier("read_datasource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean(name = "namedJdbcTemplateRead")
	public NamedParameterJdbcTemplate jdbcTemplateRead(@Qualifier("read_datasource") DataSource ds) {
		return new NamedParameterJdbcTemplate(ds);
	}

}
