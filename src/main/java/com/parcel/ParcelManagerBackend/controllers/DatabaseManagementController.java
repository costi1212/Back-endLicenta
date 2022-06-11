package com.parcel.ParcelManagerBackend.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/managedatabase")
public class DatabaseManagementController {
    @PostMapping("/addToHdfs")
    public void AddParcel(){

    }

}
