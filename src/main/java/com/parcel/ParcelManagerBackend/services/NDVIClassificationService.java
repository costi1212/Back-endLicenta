package com.parcel.ParcelManagerBackend.services;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.parcel.ParcelManagerBackend.models.request.NDVIRequest;
import com.parcel.ParcelManagerBackend.models.response.NDVIResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class NDVIClassificationService {

    @Value("${ndvi.classification.url}")
    private String ndviClassificationUrl;

    private final RestTemplate restTemplate;

    public NDVIClassificationService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getClassificationPlainJson(NDVIRequest ndviRequest){
        String url = "http://" + ndviClassificationUrl + "/ndvi/api/json/v1/ndvi-classification";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<NDVIRequest> entity = new HttpEntity<>(ndviRequest, headers);
        return this.restTemplate.postForObject(url, entity, String.class);
    }

}
