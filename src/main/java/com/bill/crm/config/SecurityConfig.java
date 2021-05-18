package com.bill.crm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Qualifier("userService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

                http.authorizeRequests()
                .antMatchers("/**/update/**", "/**/delete/**").hasAnyRole("MANAGER", "SUPERUSER")
                .antMatchers("/**/add/**").hasAnyRole("OPERATOR", "SUPERUSER")
                .antMatchers("/**/view/**").hasAnyRole("OPERATOR", "MANAGER", "SUPERUSER")
                .anyRequest()
                .authenticated()
                .and().formLogin().disable()
                .httpBasic()
                .and().csrf().disable();
    }
}
