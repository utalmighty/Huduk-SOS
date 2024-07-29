package com.huduk.sos.SOS.service;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

public interface SOSService {
    
    Mono<String> save(MultipartFile file) throws IOException;
    Mono<Resource> fetch(String assetId) throws IOException;
}
