package br.com.hanniere.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"br.com.hanniere"})
@EntityScan(basePackages = {"br.com.hanniere"})
@ComponentScan(basePackages = {"br.com.hanniere"})
@EnableCaching
public class ServiceApplication {

}
