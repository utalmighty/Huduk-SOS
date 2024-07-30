package com.huduk.sos.SOS.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Random;

import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import com.huduk.sos.SOS.entity.SOS;
import com.huduk.sos.SOS.exception.SOSException;
import com.huduk.sos.SOS.repository.SOSRepository;

@Service
@Transactional
public class SOSServiceImpl implements SOSService{

    private final SOSRepository repository;

    private final Environment environment;

    private final Random random;

    public SOSServiceImpl(SOSRepository repository, Environment environment) {
        this.repository = repository;
        this.environment = environment;
        this.random = new Random();
    }

    @Override
    public Mono<String> save(FilePart file) {
        String fileName = file.filename();
        String path = environment.getProperty("storage.path") + fileName;
        
        return file.transferTo(Path.of(path))
            .then(generateId().flatMap(id -> {
                    SOS entity = new SOS();
                    entity.setId(id);
                    entity.setFileName(fileName);
                    entity.setLocation(path);
                    entity.setLastUsedOn(LocalDate.now());
                    return repository.save(entity);
        }).map(SOS::getId))
        .onErrorResume(err-> Mono.error(new IOException()));
    }

    private Mono<String> generateId() {
        int size = 7;
        String id = generateRandom(size);
        return repository.existsById(id)
            .flatMap(exists-> Boolean.TRUE.equals(exists) ? generateId() : Mono.just(id));
    }

    private String generateRandom(int size) {
        String space = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(size);

        for (int i=0; i<size; i++) {
            int randomInt = random.nextInt(space.length());
            sb.append(space.charAt(randomInt));
        }
        return sb.toString();
    }

    @Override
    public Mono<Resource> fetch(String assetId) {
        return repository.findById(assetId)
            .switchIfEmpty(Mono.error(new SOSException("SERVICE.NOT_FOUND", HttpStatus.NOT_FOUND)))
            .map(entity-> {
                String fullFilePath = entity.getLocation();
                Path path = Path.of(fullFilePath);
                
                Resource file = null;
                try {
                    file = new UrlResource(path.toUri());
                } catch (MalformedURLException e) {
                    // TODO Refactor this
                    throw new RuntimeException(e);
                }
                if (file.exists() || file.isReadable())
                    return file;
		        else
			        throw new SOSException("SERVICE.FILE_LOST", HttpStatus.NOT_FOUND);
        });
        
    }
    
}
