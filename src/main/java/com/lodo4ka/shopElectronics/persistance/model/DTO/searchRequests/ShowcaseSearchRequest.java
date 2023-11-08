package com.lodo4ka.shopElectronics.persistance.model.DTO.searchRequests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ShowcaseSearchRequest {
    private final String type;

    private final String address;

    private final String createdAfter;

    private final String createdBefore;

    private final String updatedAfter;

    private final String updateBefore;

    @JsonCreator
    public ShowcaseSearchRequest(@JsonProperty("type") String type,
                                 @JsonProperty("address") String address,
                                 @JsonProperty("createdAfter") String createdAfter,
                                 @JsonProperty("createdBefore") String createdBefore,
                                 @JsonProperty("updatedAfter")String updatedAfter,
                                 @JsonProperty("updateBefore")String updateBefore) {
        this.type = type;
        this.address = address;
        this.createdAfter = createdAfter;
        this.createdBefore = createdBefore;
        this.updatedAfter = updatedAfter;
        this.updateBefore = updateBefore;
    }

    public String getType() {
        return type;
    }

    public String getAddress() {
        return address;
    }

    public String getCreatedAfter() {
        return createdAfter;
    }

    public String getCreatedBefore() {
        return createdBefore;
    }

    public String getUpdatedAfter() {
        return updatedAfter;
    }

    public String getUpdateBefore() {
        return updateBefore;
    }
}
