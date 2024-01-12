package com.example.glovodata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        return http.formLogin(form ->
                form.permitAll())
                .authorizeRequests(authorize ->authorize
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/health").hasRole("USER")
                        .anyRequest().authenticated())
                .logout(logout ->logout.permitAll())
                .build();
    }


    @Bean
    public UserDetailsService userDetailsService(){
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        System.out.println(encoder.encode("password"));
        UserDetails user =
                User
                        .withUsername("user")
                        .password("{bcrypt}$2a$10$hi60xlhPVMFBTp9kbFkl7uzLuuzaBtFlPgqHviv3kwiMOygB33ruq")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}
