package com.huduk.sos.SOS.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.huduk.sos.SOS.service.SOSService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("huduk/sos")
public class Controller {

    @Autowired
    private SOSService service;

    @PostMapping()
    public ResponseEntity<String> save(@RequestPart("file") MultipartFile file) throws IOException {
        return new ResponseEntity<String>(service.save(file), HttpStatus.CREATED);
    }

    @GetMapping("{assetId}")
    public ResponseEntity<MultipartFile> fetch(@PathVariable String assetId) throws IOException {
        return new ResponseEntity<>(service.fetch(assetId), HttpStatus.OK);
    }
    
}