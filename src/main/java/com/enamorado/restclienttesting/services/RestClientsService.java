package com.enamorado.restclienttesting.services;

import com.enamorado.restclienttesting.model.GameIndex;
import com.enamorado.restclienttesting.model.Pokemon;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

@Data
@Service
public class RestClientsService {

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

    public RestClientsService(RestTemplate restTemplate, RestClient restClient, WebClient webClient, HttpClient httpClient) {
        this.restTemplate = restTemplate;
        this.restClient = restClient;
        this.webClient = webClient;
        this.httpClient = httpClient;
    }

    public void callRestTemplate() {
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

    public void callRestClient() {
        System.out.println("\nCalling RestClient...");

        ResponseEntity<Pokemon> response = restClient.get()
                .uri("https://pokeapi.co/api/v2/pokemon/ditto")
                .retrieve()
                .toEntity(Pokemon.class);

        Pokemon pokemon = response.getBody();

        List<GameIndex> gameIndices = pokemon.gameIndices();

        List<GameIndex> filteredList = gameIndices.stream().filter(gameIndex -> gameIndex.gameIndex() == 76).toList();

        System.out.println(filteredList);
        System.out.println(gameIndices);

        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Pokemon via RestClient: " + response.getBody());
    }

    public void callWebClient(){
        System.out.println("\nCalling WebClient...");

        ResponseEntity<Pokemon> response = webClient.get()
                .uri("https://pokeapi.co/api/v2/pokemon/ditto")
                .retrieve()
                .toEntity(Pokemon.class)
                .block();

        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Pokemon via WebClient: " + response.getBody());
    }

    public void callHttpClient() throws Exception{
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
