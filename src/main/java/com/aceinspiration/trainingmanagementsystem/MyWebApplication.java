package com.aceinspiration.trainingmanagementsystem;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
@EnableWebSecurity
public class MyWebApplication extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				 .antMatchers("/").permitAll() 				
				  .antMatchers("/admin/**").hasAnyRole("Admin","Course","Teacher","Batch",
				  "Student","Attendance","Enroll","Report","ADMIN")
				  
		.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/admin")
		.and()
			.logout()
			.logoutSuccessUrl("/")
			.deleteCookies("JSESSIONID")
			 .and()
	            .csrf().disable().cors();
			;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.passwordEncoder(encoder)
			.withUser("admin@gmail.com")
			.password(encoder.encode("admin"))
			.roles("ADMIN");
		auth.jdbcAuthentication()
			.passwordEncoder(encoder)
			.dataSource(dataSource)
			.usersByUsernameQuery("select mail,pass,enable from admin where mail=?")
			.authoritiesByUsernameQuery("select mail,role from permission where mail=?");
	}
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public HttpFirewall defaultHttpFirewall() {
		return new DefaultHttpFirewall();
	}
}
