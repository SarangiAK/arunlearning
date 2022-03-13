package com.curewell.invoicemanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDeatilsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDeatilsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
	    String[] staticResources  =  {
	            "/css/**",
	            "resources/images/**",
	            "/resources/**",
	            "/scripts/**","/images/**","/WEB-INF/images/**"
	        };


		http.authorizeRequests().antMatchers(staticResources).permitAll()
		.mvcMatchers(HttpMethod.POST, "/curewell/registerinvoiceaddmore/{id:^[0-9]*$}", "/index", "/registerinvoiceaddmore",
				"/curewell/registerinvoicepostitem", "/curewell/registerinvoiceaddmore/registerinvoicepostitem")
		.hasAnyRole("USER", "ADMIN")
		.mvcMatchers(HttpMethod.GET, "/curewell/registerinvoice","/curewell/registerinvoiceaddmore/registerinvoice").hasRole("ADMIN")
		.mvcMatchers(HttpMethod.POST, "/getCoupon").hasAnyRole("USER", "ADMIN")
		.mvcMatchers(HttpMethod.POST, "/curewell/registerinvoicepost", "/registerinvoicepost","/curewell/registerinvoiceaddmore/registerinvoice","/curewell/registerinvoiceaddmore/registerinvoicepost").hasRole("ADMIN")
		.mvcMatchers("/curewell", "/curewell/login", "/curewell/logout", "/showReg", "/registerUser").permitAll().anyRequest().denyAll()
		.and().logout().logoutSuccessUrl("/curewell/").and().headers().httpStrictTransportSecurity().disable();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
