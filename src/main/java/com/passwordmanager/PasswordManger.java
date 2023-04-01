package com.passwordmanager;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.passwordmanager" })
@EnableJpaRepositories(basePackages = "com.passwordmanager.repositories")
@EnableTransactionManagement
@EntityScan(basePackages = { "com.passwordmanager.domain", "com.passwordmanager.domain.vo" })
public class PasswordManger extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PasswordManger.class);
    }

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(PasswordManger.class);
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);
    }
}
