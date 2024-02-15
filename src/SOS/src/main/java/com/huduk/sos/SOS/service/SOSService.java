package com.huduk.sos.SOS.service;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface SOSService {
    
    public String save(MultipartFile file) throws IOException;
    public Resource fetch(String assetId) throws IOException;
}
