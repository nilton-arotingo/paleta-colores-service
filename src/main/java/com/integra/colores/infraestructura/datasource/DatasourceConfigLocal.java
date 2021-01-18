package com.integra.colores.infraestructura.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Profile("local")
@Configuration
public class DatasourceConfigLocal implements DatasourceConfig {
	
	@Value("${spring.datasource.url}")
    private String jdbcUrl;
	
	@Value("${spring.datasource.driver-class-name}")
    private String driver;
    
    @Value("${spring.datasource.username}")
    private String username;
    
    @Value("${spring.datasource.password}")
    private String password;
    
    @Value("${spring.datasource.hikari.minimum-idle}")
    private int minimumIdle;

    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private int maxPoolSize;

	@Override
	public DataSource dataSourceIntegra() {
		 HikariConfig hikariConfig = new HikariConfig();
	        hikariConfig.setJdbcUrl(jdbcUrl);
	        hikariConfig.setDriverClassName(driver);
	        hikariConfig.setUsername(username);
	        hikariConfig.setPassword(password);
	        hikariConfig.setMaximumPoolSize(maxPoolSize);
	        hikariConfig.setMinimumIdle(minimumIdle);
	        hikariConfig.setConnectionTestQuery("SELECT 1");
	        hikariConfig.setPoolName("springHikariCP");
	        return new HikariDataSource(hikariConfig);
	}

}
