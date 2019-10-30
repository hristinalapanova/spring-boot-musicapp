package com.ga.musicappboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Configuration
@Profile("dev")
public class MusicAppBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicAppBootApplication.class, args);
    }

}
