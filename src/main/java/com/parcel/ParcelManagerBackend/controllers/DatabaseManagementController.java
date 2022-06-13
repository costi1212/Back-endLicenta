package com.parcel.ParcelManagerBackend.controllers;


import com.parcel.ParcelManagerBackend.services.HadoopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/managedatabase")
public class DatabaseManagementController {

    @Autowired
    HadoopService hadoopService;

    @PutMapping(value = "/addToHdfs", consumes = "application/json", produces = "text/plain" )
    public ResponseEntity<String> AddParcel(@RequestParam String user,
                                            @RequestParam String id,
                                            @RequestBody String json){

      return hadoopService.AddJson(json, user, id);
    }

}
