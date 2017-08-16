package br.com.hanniere.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"br.com.hanniere"})
@EntityScan(basePackages = {"br.com.hanniere"})
@ComponentScan(basePackages = {"br.com.hanniere"})
@EnableCaching
public class SpringKalahApplication {
    public static void main(String[] args) {
    	//Item readValue = mapper.readValue(json, Pit.class);
        SpringApplication.run(SpringKalahApplication.class, args);
    }
}
