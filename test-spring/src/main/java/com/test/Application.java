package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;


//embedded tomcat 
@SpringBootApplication
//@EnableScheduling
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
		
		/*Mailer mail = new Mailer();
		mail.send("kam191095@gmail.com", "ravijayakamesh", "kam191095@gmail.com", "kk", "super");*/
	}

}


//external tomcat
/*
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
  public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
      return builder.sources(Application.class);
  }
}*/