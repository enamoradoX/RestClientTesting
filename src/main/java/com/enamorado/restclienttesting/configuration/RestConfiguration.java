package com.enamorado.restclienttesting.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.http.HttpClient;

@Configuration
@ConfigurationProperties(prefix = "rest.client")
public class RestConfiguration {

    private int connectTimeoutMs;
    private int readTimeoutMs;

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectTimeoutMs);
        requestFactory.setReadTimeout(readTimeoutMs);
        return new RestTemplate(requestFactory);
    }

    @Bean
    public RestClient restClient(){
        return RestClient.builder().build();
    }

    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }

    @Bean
    public HttpClient httpClient(){
        return HttpClient.newHttpClient();
    }
}
