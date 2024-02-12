package com.huduk.sos.SOS.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.huduk.sos.SOS.entity.SOS;

public interface SOSRepository extends MongoRepository<SOS, String>{
    
}
