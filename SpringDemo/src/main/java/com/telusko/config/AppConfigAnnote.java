package com.telusko.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.telusko.SpringDemo") // we are telling Spring to scan mention package to find if @Component is used in any class and if used then create bean objects for those classes annotated with @Component
public class AppConfigAnnote {

}
