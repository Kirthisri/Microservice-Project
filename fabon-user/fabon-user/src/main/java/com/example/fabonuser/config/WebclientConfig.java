package com.example.fabonuser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;


@Configuration
public class WebclientConfig {

	
//	@Autowired
//	public LoadBalancedExchangeFilterFunction filterFunction;
//	
//	/**
//	 * product service is registered to service registry
//	 * in order to connect to the service filter the load balance service
//	 * @return
//	 */
//
//	@Bean
//	public WebClient productsWebClient() {
//		return WebClient.builder().baseUrl("http://product-service").filter(filterFunction).build();
//	}
//	
//	/**
//	 * in order to connect to product service utilise the webclient
//	 */
//
//	@Bean(name = "userProductClient")
//	public UserProductClient userProductClient() {
//		HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
//				.builder(WebClientAdapter.forClient(productsWebClient())).build();
//		return httpServiceProxyFactory.createClient(UserProductClient.class);
//	}
//
//	@Bean
//    public WebClient.Builder webClientBuilder() {
//        return WebClient.builder();
//    }

	/*
	 * @Bean(name="userProductClient") public WebClient
	 * userProductClient(WebClient.Builder webClientBuilder) { // The service name
	 * "cart-service" should match the name registered in Eureka return
	 * webClientBuilder .baseUrl("http://product-service") .filter(filterFunction)
	 * .build(); }
	 */
	
}
