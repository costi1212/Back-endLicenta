package com.parcel.ParcelManagerBackend.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HadoopService {
    @Value("${ndvi.classification.url}")
    private String ndviClassificationUrl;

    private final RestTemplate restTemplate;

    public HadoopService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    //void PutParcel()

}
