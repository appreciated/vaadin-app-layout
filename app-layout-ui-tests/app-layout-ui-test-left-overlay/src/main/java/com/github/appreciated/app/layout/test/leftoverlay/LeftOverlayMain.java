package com.github.appreciated.app.layout.test.leftoverlay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
public class LeftOverlayMain extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(LeftOverlayMain.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LeftOverlayMain.class);
    }
}
