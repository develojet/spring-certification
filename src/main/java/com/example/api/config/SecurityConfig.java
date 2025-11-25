package com.example.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    // 1. Define the authorized user explicitly for testing
    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserDetails user1 = User.withUsername("user1") // Matches your TestRestTemplate call
                .password(encoder.encode("password1")) // Matches your TestRestTemplate call
                .roles("USER") // Assign the role you defined in your application.properties
                .build();

        // This is a common test practice to add users
        return new InMemoryUserDetailsManager(user1);
    }

    // 2. Configure Authorization Rules
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Allow "USER" role to access cashcard endpoints
                        .requestMatchers("/cashcards/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                // Configure to use Basic Authentication, which your test uses
                .httpBasic(httpBasic -> {})
                // Disable CSRF for testing convenience (important for POST/PUT)
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
