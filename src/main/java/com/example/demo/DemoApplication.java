package com.example.demo;
import com.example.demo.security.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/users/{id}").permitAll()
				.antMatchers(HttpMethod.GET, "/users").permitAll()
				.antMatchers(HttpMethod.POST, "/users").permitAll()
				.antMatchers(HttpMethod.PUT, "/users/{id}").permitAll()
				.antMatchers(HttpMethod.GET, "/ingredients/{id}").permitAll()
				.antMatchers(HttpMethod.GET, "/ingredients").permitAll()
				.antMatchers(HttpMethod.POST, "/ingredients").permitAll()
				.antMatchers(HttpMethod.PUT, "/ingredients/{id}").permitAll()
				.antMatchers(HttpMethod.DELETE, "/ingredients/{id}").permitAll()
				.antMatchers(HttpMethod.POST, "/pizzas").permitAll()
				.anyRequest().authenticated();
				http.cors();
		}
	}
}
