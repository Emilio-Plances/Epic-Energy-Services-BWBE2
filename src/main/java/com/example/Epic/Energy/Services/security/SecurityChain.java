package com.example.Epic.Energy.Services.security;

import com.example.Epic.Energy.Services.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityChain {
    @Autowired
    private JwtTools jwtTools;
    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.cors(AbstractHttpConfigurer::disable);

        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/api/auth/**").permitAll());
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.GET,"/api/**").permitAll());
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/api/**").hasAuthority(Role.ADMIN.name()));
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/**").denyAll());


        return httpSecurity.build();
    }
}
