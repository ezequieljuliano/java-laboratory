package com.ezequieljuliano.bookmark.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
	return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	try {
	    http.csrf().disable();
	    http
		    .authorizeRequests()
		    .antMatchers("/js/").permitAll()
		    .antMatchers("/css/").permitAll()
		    .antMatchers("/img/").permitAll()
		    .antMatchers("/cfg/").permitAll()
		    .antMatchers("/javax.faces.resource/**").permitAll()
		    .anyRequest().authenticated()
		    .and()
		    .formLogin().loginPage("/login.jsf").permitAll()
		    .failureUrl("/login.jsf?error=true")
		    .defaultSuccessUrl("/index.jsf")
		    .and()
		    .logout()
		    .logoutSuccessUrl("/login.jsf");
	} catch (Exception ex) {
	    throw new RuntimeException(ex);
	}
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

}
