package com.lodo4ka.shopElectronics.persistance.model.DTO;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ShowcaseSearchRequest {

    private final String type;

    private final String address;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final Date creationDate1;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final Date creationDate2;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final Date modificationDate1;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final Date modificationDate2;

    @JsonCreator
    public ShowcaseSearchRequest(@JsonProperty("type") String type,
                                 @JsonProperty("address") String address,
                                 @JsonProperty("creationDate1") Date creationDate1,
                                 @JsonProperty("creationDate2") Date creationDate2,
                                 @JsonProperty("modificationDate1")Date modificationDate1,
                                 @JsonProperty("modificationDate2")Date modificationDate2) {
        this.type = type;
        this.address = address;
        this.creationDate1 = creationDate1;
        this.creationDate2 = creationDate2;
        this.modificationDate1 = modificationDate1;
        this.modificationDate2 = modificationDate2;
    }

    public String getType() {
        return type;
    }

    public String getAddress() {
        return address;
    }

    public Date getCreationDate1() {
        return creationDate1;
    }

    public Date getCreationDate2() {
        return creationDate2;
    }

    public Date getModificationDate1() {
        return modificationDate1;
    }

    public Date getModificationDate2() {
        return modificationDate2;
    }
}
