package me.qebs.restdemo.config;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;

public class CustomRestTemplate extends RestTemplate {
    private RestTemplate restTemplate;

    public CustomRestTemplate(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) throws IOException {
        return this.restTemplate.getRequestFactory().createRequest(uri,httpMethod);
    }
}
