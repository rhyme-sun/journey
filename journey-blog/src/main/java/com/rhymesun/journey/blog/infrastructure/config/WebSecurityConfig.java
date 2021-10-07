package com.rhymesun.journey.blog.infrastructure.config;

import java.io.PrintWriter;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecurityProperties securityProperties;

    @Autowired
    public WebSecurityConfig(final SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        final SecurityProperties.User user = securityProperties.getUser();
        final List<String> roles = user.getRoles();
        auth.inMemoryAuthentication()
                .withUser(user.getName())
                .password(user.getPassword())
                .roles(roles.toArray(new String[roles.size()]));
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginProcessingUrl("/journey/login")
                .successHandler((request, response, authentication) -> {
                    final Object principal = authentication.getPrincipal();
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    final PrintWriter out = response.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(principal));
                    out.flush();
                    out.close();
                })
                .failureHandler((request, response, e) -> {
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    final PrintWriter out = response.getWriter();
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    out.write(e.getMessage());
                    out.flush();
                    out.close();
                })
                .and()
                .logout()
                .logoutUrl("/journey/logout")
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    final PrintWriter out = response.getWriter();
                    if (authentication == null) {
                        out.write("Not login");
                    } else {
                        out.write(new ObjectMapper().writeValueAsString(authentication.getPrincipal()));
                    }
                    out.flush();
                    out.close();
                })
                .deleteCookies()
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .and()
                .cors()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, e) -> {
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    final PrintWriter out = response.getWriter();
                    out.write(e.getMessage());
                    out.flush();
                    out.close();
                });
    }
}
