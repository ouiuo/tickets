package com.example.tickets.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class WebMvcConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        auth.authenticationProvider(authenticationProvider);

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Headers", "Access-Control-Allow-Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", "Origin", "Cache-Control", "Content-Type", "Authorization"));
        configuration.setAllowedMethods(Arrays.asList("DELETE", "GET", "POST", "PATCH", "PUT"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/users/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/tickets/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .and()
                .logout().logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and().formLogin().permitAll()
                .and().httpBasic().authenticationEntryPoint(authenticationEntryPoint);

    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/").setViewName("/tickets.html");
            registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

}
