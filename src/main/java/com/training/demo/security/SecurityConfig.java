package com.training.demo.security;


import com.training.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserService userDetailsService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    AuthenticationManager authenticationProvider() throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());

        return new ProviderManager(provider);
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(auth -> {
                    auth.requestMatchers("/", "/login", "/register", "/auth/{username}", "/auth/{id}").permitAll();
                    auth.requestMatchers(HttpMethod.DELETE,"/api/blog-posts/{id}").authenticated();
                    auth.requestMatchers(HttpMethod.POST,"/api/blog-posts").authenticated();
                    auth.requestMatchers(HttpMethod.GET,"/api/blog-posts").permitAll();
                    auth.requestMatchers(HttpMethod.PUT,"/api/blog-posts/{id}").authenticated();
                    auth.requestMatchers(HttpMethod.DELETE,"/comments/{id}").authenticated();
                    auth.requestMatchers(HttpMethod.POST,"/comments").authenticated();
                    auth.requestMatchers(HttpMethod.GET,"/comments").permitAll();
                    auth.requestMatchers(HttpMethod.PUT,"/comments/{id}").authenticated();
                })
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(withDefaults())
                .authenticationManager(authenticationProvider());

        return http.build();
    }

}
