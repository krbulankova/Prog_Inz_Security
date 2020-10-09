package com.example.demo.conf;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configurable
@EnableWebSecurity
public class WebSecurityConfigDB extends WebSecurityConfigurerAdapter{

	
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		UserDetails u1 = User.withDefaultPasswordEncoder()
				.username("karina")
				.password("123")
				.roles("USER")
				.build();
		
		
		
		UserDetails u2 = User.withDefaultPasswordEncoder()
				.username("janis")
				.password("321")
				.roles("ADMIN")
				.build();
		
		
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(u1, u2);
		return manager;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/product/showAllProducts").permitAll()//anonymous
		.antMatchers("/product/showAllProducts/**").anonymous()
		.antMatchers("/product/insertOneProduct").hasAnyRole("ADMIN")
		.antMatchers("/h2-console/**").hasAnyRole("ADMIN")
		.antMatchers("/customer/buy/**").hasAnyRole("USER", "ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin().permitAll()
		.and()
		.logout().permitAll();
		
		//only for h2-console in web
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		
	}

	
	
}
