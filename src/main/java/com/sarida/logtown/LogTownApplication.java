package com.sarida.logtown;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LogTownApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogTownApplication.class, args);
    }
}
