package com.heeju.bootmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BootMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootMvcApplication.class, args);
    }

}
