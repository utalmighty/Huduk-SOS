package com.huduk.sos.SOS.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "SOS")
public class SOS {
    
    @Id
    private String id;

    private String location;

    private String fileName;

    private LocalDate lastUsedOn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDate getLastUsedOn() {
        return lastUsedOn;
    }

    public void setLastUsedOn(LocalDate lastUsedOn) {
        this.lastUsedOn = lastUsedOn;
    }

    

}