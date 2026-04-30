package com.enamorado.restclienttesting;

import com.enamorado.restclienttesting.model.Pokemon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import java.net.http.HttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@SpringBootApplication
public class RestClientTestingApplication implements CommandLineRunner {

    @Value("${restclient.read-timeout-ms:3000}")
    private int readTimeoutMs;

    // Springs older synchronous http client
    private final RestTemplate restTemplate;

    // Springs newer synchronous http client
    private final RestClient restClient;

    // Springs newer reactive http client
    private final WebClient webClient;

    // Java's built-in http client
    private final HttpClient httpClient;

    public RestClientTestingApplication(RestTemplate restTemplate, RestClient restClient, WebClient webClient, HttpClient httpClient) {
        this.restTemplate = restTemplate;
        this.restClient = restClient;
        this.webClient = webClient;
        this.httpClient = httpClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(RestClientTestingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception{
        System.out.println("App started! Args count: " + args.length);

        callRestTemplate();
        callRestClient();
        callWebClient();
        callHttpClient();

    }

    private void callRestTemplate() {
        System.out.println("\nCalling RestTemplate...");


        ResponseEntity<Pokemon> response = restTemplate.exchange(
                "https://pokeapi.co/api/v2/pokemon/ditto",
                HttpMethod.GET,
                null,
                Pokemon.class
        );

        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Pokemon name: " + response.getBody());
    }

    private void callRestClient() {
        System.out.println("\nCalling RestClient...");

        ResponseEntity<Pokemon> response = restClient.get()
                .uri("https://pokeapi.co/api/v2/pokemon/ditto")
                .retrieve()
                .toEntity(Pokemon.class);

        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Pokemon via RestClient: " + response.getBody());
    }

    private void callWebClient(){
        System.out.println("\nCalling WebClient...");

        ResponseEntity<Pokemon> response = webClient.get()
                .uri("https://pokeapi.co/api/v2/pokemon/ditto")
                .retrieve()
                .toEntity(Pokemon.class)
                .block();

        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Pokemon via WebClient: " + response.getBody());
    }

    private void callHttpClient() throws Exception{
        System.out.println("\nCalling HttpClient...");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://pokeapi.co/api/v2/pokemon/ditto"))
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.USER_AGENT, "RestClientTesting/1.0")
                .timeout(Duration.ofMillis(readTimeoutMs))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();

        Pokemon pokemon = objectMapper.readValue(response.body(), Pokemon.class);

        System.out.println("Status code: " + response.statusCode());
        System.out.println("Pokemon via HttpClient: " + pokemon);

    }

}
