package com.lodo4ka.shopElectronics.persistance.model.DTO.searchRequests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class ProductSearchRequest {

    private final UUID showcaseId;

    private final String type;

    private final Double priceMoreThan;

    private final Double priceLessThan;

    @JsonCreator
    public ProductSearchRequest(@JsonProperty("showcaseId") UUID showcaseId,
                                @JsonProperty("type") String type,
                                @JsonProperty("priceMoreThan") Double priceMoreThan,
                                @JsonProperty("priceLessThan") Double priceLessThan) {
        this.showcaseId = showcaseId;
        this.type = type;
        this.priceMoreThan = priceMoreThan;
        this.priceLessThan = priceLessThan;
    }

    public UUID getShowcaseId() {
        return showcaseId;
    }

    public String getType() {
        return type;
    }

    public Double getPriceMoreThan() {
        return priceMoreThan;
    }

    public Double getPriceLessThan() {
        return priceLessThan;
    }

}
