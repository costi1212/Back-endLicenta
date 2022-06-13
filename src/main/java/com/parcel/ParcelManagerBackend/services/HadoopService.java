package com.parcel.ParcelManagerBackend.services;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@Service
public class HadoopService {
    @Value("${ndvi.classification.url}")
    private String ndviClassificationUrl;

    @Value("${webhdfs.url}")
    private String webHdfsUrl;

    private final RestTemplate restTemplate;

    public HadoopService(RestTemplateBuilder restTemplateBuilder) {

        this.restTemplate = restTemplateBuilder.build();
    }

    public ResponseEntity<String> AddJson(String json, String user, String parcelId){
        String url = webHdfsUrl + user + "/parcels/" + parcelId + ".txt";
        URI finalUrl = UriComponentsBuilder.fromUriString(url).queryParam("user.name", user).
                queryParam("op", "CREATE").build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> body = new HttpEntity<String>(json);
        return this.restTemplate.exchange(finalUrl.toString(), HttpMethod.PUT, body, String.class);
    }

}
