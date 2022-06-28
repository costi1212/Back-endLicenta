package com.parcel.ParcelManagerBackend.controllers;


import com.parcel.ParcelManagerBackend.services.HDFSService;
import com.parcel.ParcelManagerBackend.services.HadoopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/managedatabase")
public class DatabaseManagementController {

    @Autowired
    HadoopService hadoopService;

    @Autowired
    HDFSService hdfsService;

    @PutMapping(value = "webhdfs/addJsonToHdfs", consumes = "application/json", produces = "text/plain" )
    public ResponseEntity<String> AddParcelJson(@RequestParam String user,
                                            @RequestParam String id,
                                            @RequestBody String json){

      return hadoopService.AddJson(json, user, id);
    }

    @PutMapping(value = "webhdfs/addFilesToHdfs")
    public String AddParcelFiles(@RequestParam String user) throws IOException {

      return hadoopService.AddAllFiles(user);
    }

    @PutMapping(value="/addParcel")
    public void AddParcel(@RequestParam String localFileName) throws IOException {
        hdfsService.copyFromLocalFile(localFileName);
    }

    @PostMapping(value= "/addAllParcels")
    void AddAll() throws IOException {
        hdfsService.copyAll();
    }

    @PostMapping(value="/createFolder")
    public void createFolder(@RequestParam String dirName) throws IOException {
        hdfsService.createDir(dirName);
    }


}
