package com.nikhil.cms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public class apiFacility {
     @Value("${api.baseurl}")
    private String baseUrl;  // Define the API base URL in application.properties or application.yml

    private final RestTemplate restTemplate;

    public apiFacility(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String fetchDataFromApi() {
        String apiUrl = baseUrl + "/api/endpoint";  // Replace with your API endpoint

        // Make GET request
        String result = restTemplate.getForObject(apiUrl, String.class);

        return result;
    }
}
