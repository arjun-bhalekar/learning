package com.learn.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * To perform custom routing & filtering for services
 * 
 * @author ArjunB
 *
 */

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {

		// default
		// return builder.routes().build();

		return builder.routes()
				 .route(p -> p.path("/get")
						 .filters(f -> f.addRequestHeader("myheaderTEST", "TEST 12345")).uri("http://httpbin.org:80"))
				 .route(p -> p.path("/currency-exchange/**").uri("lb://currency-exchange"))
				 .route(p -> p.path("/currency-conversion/**").uri("lb://currency-conversion"))
				 .route(p -> p.path("/currency-conversion-feign/**").uri("lb://currency-conversion"))
				 .build();

	}

}
