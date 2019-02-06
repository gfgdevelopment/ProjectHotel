package br.com.iftm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class) // adiciona
public class ProjectHotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectHotelApplication.class, args);
	}

}
