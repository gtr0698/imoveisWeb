package com.projeto.imoveis.configuration;

import com.projeto.imoveis.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    CustomUserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/pessoas/criar").permitAll()
                .requestMatchers(HttpMethod.GET, "/pessoas/listar").authenticated()
                .requestMatchers(HttpMethod.GET, "/pessoas/buscar/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/pessoas/atualizar/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/pessoas/remover/**").authenticated()

                .requestMatchers(HttpMethod.GET, "/imoveis/listar").authenticated()
                .requestMatchers(HttpMethod.GET, "/imoveis/buscar/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/imoveis/criar").authenticated()
                .requestMatchers(HttpMethod.PUT, "/imoveis/atualizar/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/imoveis/remover/**").authenticated()

                .requestMatchers(HttpMethod.GET, "/funcionarios/listar").authenticated()
                .requestMatchers(HttpMethod.POST, "/funcionarios/criar").authenticated();
                //.requestMatchers(HttpMethod.GET, "/funcionarios/buscar/**").authenticated()
                //.requestMatchers(HttpMethod.PUT, "/funcionarios/atualizar/**").authenticated()
                //.requestMatchers(HttpMethod.DELETE, "/funcionarios/remover/**").authenticated();
        return http.build();
    }

}
