package com.gsg.starwarsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class StarwarsapiApplication {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(StarwarsapiApplication.class, args);
	}

}
