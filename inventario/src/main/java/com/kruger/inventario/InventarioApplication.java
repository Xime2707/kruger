package com.kruger.inventario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

//@SpringBootApplication
@EnableAuthorizationServer
/*
public class InventarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventarioApplication.class, args);
	}

}
*/
@SpringBootApplication

public class InventarioApplication {

    public static void main(String[] args) {

        SpringApplication.run(InventarioApplication.class);

    }

}


