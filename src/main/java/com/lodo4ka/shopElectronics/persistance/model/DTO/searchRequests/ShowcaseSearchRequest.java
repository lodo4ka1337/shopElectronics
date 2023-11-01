package com.lodo4ka.shopElectronics.persistance.model.DTO.searchRequests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ShowcaseSearchRequest {
    private final String type;

    private final String address;

    private final String creationDate1;

    private final String creationDate2;

    private final String modificationDate1;

    private final String modificationDate2;

    @JsonCreator
    public ShowcaseSearchRequest(@JsonProperty("type") String type,
                                 @JsonProperty("address") String address,
                                 @JsonProperty("creationDate1") String creationDate1,
                                 @JsonProperty("creationDate2") String creationDate2,
                                 @JsonProperty("modificationDate1")String modificationDate1,
                                 @JsonProperty("modificationDate2")String modificationDate2) {
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

    public String getCreationDate1() {
        return creationDate1;
    }

    public String getCreationDate2() {
        return creationDate2;
    }

    public String getModificationDate1() {
        return modificationDate1;
    }

    public String getModificationDate2() {
        return modificationDate2;
    }
}
