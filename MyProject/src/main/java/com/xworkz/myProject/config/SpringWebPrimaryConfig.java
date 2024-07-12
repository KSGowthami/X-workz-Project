package com.xworkz.myProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("com.xworkz.myProject")
@PropertySource("classpath:application.properties")
@EnableWebMvc
public class SpringWebPrimaryConfig implements WebMvcConfigurer {

    public SpringWebPrimaryConfig(){
        System.out.println("Running SpringWebPrimaryConfig");
    }


    @Bean
    public ViewResolver viewResolver(){
        System.out.println("Running View resolver");
        return new InternalResourceViewResolver("/",".jsp");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/javascript/**").addResourceLocations("/javascript/");
    }
}
