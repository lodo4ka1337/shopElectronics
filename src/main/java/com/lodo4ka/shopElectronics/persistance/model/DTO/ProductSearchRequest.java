package com.lodo4ka.shopElectronics.persistance.model.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class ProductSearchRequest {
    @NotNull
    private final UUID showcaseId;

    private final String type;

    private final Double price1;

    private final Double price2;

    @JsonCreator
    public ProductSearchRequest(@JsonProperty("showcaseId") UUID showcaseId,
                                @JsonProperty("type") String type,
                                @JsonProperty("price1") Double price1,
                                @JsonProperty("price2") Double price2) {
        this.showcaseId = showcaseId;
        this.type = type;
        this.price1 = price1;
        this.price2 = price2;
    }

    public UUID getShowcaseId() {
        return showcaseId;
    }

    public String getType() {
        return type;
    }

    public Double getPrice1() {
        return price1;
    }

    public Double getPrice2() {
        return price2;
    }

}
