package com.mflq.galery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {
	@Bean("clienteRest")
	public RestTemplate registrarRestTemplate() {
		return new RestTemplate();
	}

}
