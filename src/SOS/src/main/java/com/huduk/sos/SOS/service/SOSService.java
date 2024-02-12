package com.huduk.sos.SOS.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface SOSService {
    
    public String save(MultipartFile file) throws IOException;
    public MultipartFile fetch(String assetId) throws IOException;
}
