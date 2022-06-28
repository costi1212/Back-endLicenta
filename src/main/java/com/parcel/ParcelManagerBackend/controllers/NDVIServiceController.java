package com.parcel.ParcelManagerBackend.controllers;

import com.parcel.ParcelManagerBackend.models.request.NDVIRequest;
import com.parcel.ParcelManagerBackend.services.NDVIClassificationService;
import com.parcel.ParcelManagerBackend.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/NDVI")
public class NDVIServiceController {

    @Autowired
    NDVIClassificationService ndviClassificationService;

    @PostMapping(value = "/getParcelClassification", consumes = "application/json", produces = "application/json")
    String getNdviClassification(@RequestBody NDVIRequest ndviRequest){

    return ndviClassificationService.getClassificationPlainJson(ndviRequest);

    }

    @PostMapping(value = "/storeParcelClassification", consumes = "application/json", produces = "application/json")
    String storeNdviClassification(@RequestParam String parcelId,
                                   @RequestBody NDVIRequest ndviRequest) throws IOException {

        String json = ndviClassificationService.getClassificationPlainJson(ndviRequest);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        FileUtils.writeBufferedWriter("C:\\Users\\Viezure\\parcels\\" + parcelId + "-" + dtf.format(LocalDateTime.now()) + ".txt", json);
        return json;
    }



}
