package com.huduk.sos.SOS.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.huduk.sos.SOS.SosApplication;
import com.huduk.sos.SOS.service.SOSService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("huduk/sos")
public class Controller {

    private SOSService service;

    public Controller(SOSService service) {
        this.service = service;
    }

    @PostMapping()
    public Publisher<ResponseEntity<String>> save(@RequestPart("file") MultipartFile file) throws IOException {
        return service.save(file).map(key-> new ResponseEntity<>(key, HttpStatus.CREATED));
    }

    @GetMapping("{assetId}")
    public Publisher<ResponseEntity<Resource>> fetch(@PathVariable String assetId) throws IOException {
        return service.fetch(assetId).map(file-> ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
        "attachment; filename=\"" + file.getFilename() + "\"").body(file));
    }
    
}