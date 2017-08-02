package com.example.demo.config;

import org.springframework.context.annotation.Configuration;

/*@Configuration
@EnableWebSecurity*/
public class AuthConfig{
/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		// TODO Auto-generated method stub
		super.configure(auth);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http);
		http.authorizeRequests().antMatchers("/").access("admin").antMatchers("/**").permitAll()
		.and().formLogin().loginPage("/login").failureUrl("/login");
	}*/
	

}
