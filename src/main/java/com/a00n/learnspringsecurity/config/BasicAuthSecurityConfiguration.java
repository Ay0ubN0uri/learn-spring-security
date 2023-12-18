package com.a00n.learnspringsecurity.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthSecurityConfiguration {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/user").hasRole("USER")
                .anyRequest().authenticated());
        // http.formLogin(withDefaults());
        http.csrf(t -> t.disable());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    // @Bean
    // UserDetailsService detailsService() {
    // var user = User.withUsername("user")
    // .password("user")
    // .passwordEncoder(t -> passwordEncoder().encode(t))
    // .roles("USER")
    // .build();
    // var admin = User.withUsername("admin")
    // .password("admin")
    // .passwordEncoder(t -> passwordEncoder().encode(t))
    // .roles("ADMIN")
    // .build();
    // return new InMemoryUserDetailsManager(user, admin);
    // }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
