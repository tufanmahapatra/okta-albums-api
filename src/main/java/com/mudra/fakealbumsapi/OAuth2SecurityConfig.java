package com.mudra.fakealbumsapi;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/*
 * Server is set up with the following
 * 	- All requests are authenticated (they should have a valid token)
 * 	- All requests to /fakealbums/** must have scope of "photolibrary.read"
 * 	- tokens would be of JWT format
 * 	- No HttpSession will be created for a request. 
 */
@Configuration
public class OAuth2SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .cors()
          .and()
          .authorizeRequests(authz -> authz
            .antMatchers("/jacksonville/**","/newyork/**","/miami/**","/random/**")
               .permitAll()
            .antMatchers("/fakealbums/**").hasAuthority("SCOPE_photolibrary.read")
            .anyRequest().authenticated())
          .oauth2ResourceServer(oauth2 -> oauth2.jwt())
          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}
