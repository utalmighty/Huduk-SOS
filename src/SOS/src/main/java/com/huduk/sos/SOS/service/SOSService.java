package com.huduk.sos.SOS.service;

import org.springframework.core.io.Resource;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

public interface SOSService {
    
    Mono<String> save(FilePart file);
    Mono<Resource> fetch(String assetId);
}
