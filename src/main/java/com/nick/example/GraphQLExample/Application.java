package com.nick.example.GraphQLExample;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import graphql.Scalars;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/graphql").allowedOrigins("http://localhost:3000");
            }
        };
    }
}