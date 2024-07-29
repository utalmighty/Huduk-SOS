package com.huduk.sos.SOS.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.huduk.sos.SOS.entity.SOS;

public interface SOSRepository extends ReactiveMongoRepository<SOS, String>{
    
}
