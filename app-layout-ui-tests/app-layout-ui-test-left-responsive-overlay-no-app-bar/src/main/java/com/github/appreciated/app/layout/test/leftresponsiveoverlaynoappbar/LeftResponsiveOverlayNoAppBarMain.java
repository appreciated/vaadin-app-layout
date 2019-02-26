package com.github.appreciated.app.layout.test.leftresponsiveoverlaynoappbar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
public class LeftResponsiveOverlayNoAppBarMain extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(LeftResponsiveOverlayNoAppBarMain.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LeftResponsiveOverlayNoAppBarMain.class);
    }
}
