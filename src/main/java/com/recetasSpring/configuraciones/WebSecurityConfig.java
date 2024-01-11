package com.recetasSpring.configuraciones;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
	
	// AUTENTICACIÓN
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception{
		
		auth.jdbcAuthentication().passwordEncoder(passwordEncoder()).dataSource(dataSource)
			.usersByUsernameQuery("""
					SELECT email, password, 1
					FROM usuarios
					WHERE email = ?
					""")
			.authoritiesByUsernameQuery("""
					SELECT u.email, CONCAT('ROLE_', r.nombre)
					FROM usuarios u
					JOIN roles r ON u.rol_id = r.id
					WHERE email = ?
					""");
	}
	
	//AUTORIZACIÓN
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity
			.authorizeHttpRequests(requests -> requests
					.requestMatchers("/admin/**").hasRole("ADMIN")
					.requestMatchers("/receta").authenticated()
					.anyRequest().permitAll()
			)
			.exceptionHandling(handling -> handling.accessDeniedPage("/login?noautorizado"))
			.formLogin(form -> form
					.loginPage("/login")
					.permitAll()
				)
			.logout(LogoutConfigurer::permitAll);
		
		return httpSecurity.build();
	}
	
	//	AUTENTICACIÓN DE MYSQL
	//	----------------------
	//	create table users(
	//		username varchar(50) not null primary key,
	//		password varchar(500) not null,
	//		enabled boolean not null
	//	);
	//
	//	create table authorities (
	//		username varchar(50) not null,
	//		authority varchar(50) not null,
	//		constraint fk_authorities_users foreign key(username) references users(username)
	//	);
	//	create unique index ix_auth_username on authorities (username,authority);
}
