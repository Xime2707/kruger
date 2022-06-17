package com.kruger.inventario.oauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceSConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
    	
		http
			.headers()
				.frameOptions()
				.disable()
				.and()
		.authorizeRequests()
			.antMatchers("/empleado/guardar").hasAnyRole("USER", "ADMIN")
			.antMatchers("/empleado/editar").hasAnyRole("USER", "ADMIN")
			.antMatchers("/empleado/listarPorCedulaE").hasAnyRole("USER", "ADMIN")
			.antMatchers("/empleado/**").hasRole("ADMIN")
			.antMatchers("/vacuna/**").hasRole("ADMIN")

			.anyRequest().authenticated()
			.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());        
    }
}
