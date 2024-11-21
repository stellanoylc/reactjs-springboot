package com.spring.springbootdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ThirdPartyApiService {
	@Autowired
    private WebClient.Builder webClientBuilder;

    public String getThirdPartyData() {
        String apiUrl = "https://datausa.io/api/data?drilldowns=Nation&measures=Population"; // Replace with the 3rd-party API URL

        return webClientBuilder.build()
                .get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Use `.block()` for synchronous call or return Mono for reactive
    }
}
