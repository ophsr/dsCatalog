package com.devsuperior.dsCatalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// Classe de config / config do app todo/manter config / config componente especifico
@Configuration
public class AppConfig {

    //Bean Componente spring 
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
