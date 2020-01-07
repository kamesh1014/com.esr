package com.test;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class Config {
	

	
	   @Bean(name = "dbthinkService")
	   public DataSource createProductServiceDataSource() {
		   
		   System.out.println("inside bean creation of db think service");
		   
		   DataSourceBuilder ds= DataSourceBuilder.create();
		   ds.url("jdbc:mysql://localhost:3306/new");
		   ds.username("root");
		   ds.password("root");
		   
	      return ds.build();
	   }
	  
	   @Bean(name = "jdbcthinkService")
	   @Autowired
	   public JdbcTemplate createJdbcTemplate_ProductService(@Qualifier("dbthinkService") DataSource productServiceDS) {
		   System.out.println("inside bean creation of db jdbc service");
		   
		   return new JdbcTemplate(productServiceDS);
	   }
	
	

}
