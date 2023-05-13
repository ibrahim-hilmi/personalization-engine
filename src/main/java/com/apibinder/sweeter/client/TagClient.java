package com.apibinder.sweeter.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.Map;

public class TagClient {

    private WebClient webClient;

    public TagClient() {
        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofMillis(5000));
        webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl("http://localhost:8086/tag/").build();
    }

    public Map<String, Integer> getTagCounts(String uid, String tagKey){
        return webClient.get()
                .uri("count/" + uid + "/" + tagKey)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchangeToMono(response -> {
                    if (!HttpStatus.OK.equals(response.statusCode())) {
                        System.out.println("Unexpected Response" + response.statusCode());
                    }
                    return response.bodyToMono(Map.class);
                })
                .block();
    }

    public Map<String, Integer> getTagPercents(String uid, String tagKey){
        return webClient.get()
                .uri("percent/" + uid + "/" + tagKey)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchangeToMono(response -> {
                    if (!HttpStatus.OK.equals(response.statusCode())) {
                        System.out.println("Unexpected Response" + response.statusCode());
                    }
                    return response.bodyToMono(Map.class);
                })
                .block();
    }

}
