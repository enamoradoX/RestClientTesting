package com.enamorado.restclienttesting.configuration;

import io.netty.channel.ChannelOption;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import java.time.Duration;

@Data
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

        RestTemplate restTemplate = new RestTemplate(requestFactory);

        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            request.getHeaders().set(HttpHeaders.USER_AGENT, "RestClientTesting/1.0");
            return execution.execute(request, body);
        });

        return restTemplate;
    }

    @Bean
    public RestClient restClient(RestTemplate restTemplate){
        return RestClient.builder(restTemplate)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.USER_AGENT, "RestClientTesting/1.0")
                .build();
    }

    @Bean
    public WebClient webClient(){
        HttpClient reactorHttpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeoutMs)
                .responseTimeout(Duration.ofMillis(readTimeoutMs));

        return WebClient.builder().clientConnector(new ReactorClientHttpConnector(reactorHttpClient))
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.USER_AGENT, "RestClientTesting/1.0")
                .build();
    }

    @Bean
    public java.net.http.HttpClient httpClient(){
        return java.net.http.HttpClient.newBuilder()
                .connectTimeout(Duration.ofMillis(connectTimeoutMs))
                .build();
    }
}
