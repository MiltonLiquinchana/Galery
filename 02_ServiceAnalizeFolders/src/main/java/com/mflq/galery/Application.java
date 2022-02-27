package com.mflq.galery;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*Esta anotacion es importante para habilitar nuestro cliente feign, ademas permite
 * inyectar estos clientes en nuestros controladores u otros componentes de spring, ejemplo: en una clase service habilita la inyeccion de dependencias
 *(autowired)*/
@EnableFeignClients
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);
		/* Activa esto para poder mostrar los ventanas de dialogo de java */
		SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
		builder.headless(false).run(args);
	}

}
