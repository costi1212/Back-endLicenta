package com.parcel.ParcelManagerBackend.controllers;

import com.parcel.ParcelManagerBackend.models.request.NDVIRequest;
import com.parcel.ParcelManagerBackend.services.NDVIClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/NDVI")
public class NDVIServiceController {

    @Autowired
    NDVIClassificationService ndviClassificationService;

    @PostMapping(value = "/getParcelClassification", consumes = "application/json", produces = "text/plain")
    String getNdviClassification(@RequestBody NDVIRequest ndviRequest){

    return ndviClassificationService.getClassificationPlainJson(ndviRequest);

    }
}
