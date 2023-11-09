package com.lodo4ka.shopElectronics.persistance.model.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.lodo4ka.shopElectronics.persistance.model.DTO.interfaces.Details;
import com.lodo4ka.shopElectronics.persistance.model.DTO.interfaces.Exists;
import com.lodo4ka.shopElectronics.persistance.model.DTO.interfaces.New;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.UUID;

public class ProductDTO {

    @Null(groups = {New.class})
    @NotNull(groups = {Exists.class})
    @JsonView({Details.class})
    private final UUID id;

    @NotNull(groups = {New.class})
    @JsonView({Details.class})
    private final String name;

    @NotNull(groups = {New.class})
    @JsonView({Details.class})
    private final String type;

    @NotNull(groups = {New.class})
    @JsonView({Details.class})
    private final Double price;

    @NotNull(groups = {New.class})
    @JsonView({Details.class})
    private final UUID showcaseId;

    @NotNull(groups = {New.class})
    @JsonView({Details.class})
    private final Integer positionOnShowcase;

    @JsonView({Details.class})
    private final LocalDate dateOfPlacing;

    @JsonView({Details.class})
    private final LocalDate lastModificationDate;

    @JsonCreator
    public ProductDTO(@JsonProperty("id") UUID id,
                      @JsonProperty("name") String name,
                      @JsonProperty("type") String type,
                      @JsonProperty("price") Double price,
                      @JsonProperty("showcaseId") UUID showcaseId,
                      @JsonProperty("positionOnShowcase") Integer positionOnShowcase,
                      @JsonProperty("dateOfPlacing") LocalDate dateOfPlacing,
                      @JsonProperty("lastModificationDate") LocalDate lastModificationDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.showcaseId = showcaseId;
        this.positionOnShowcase = positionOnShowcase;
        this.dateOfPlacing = dateOfPlacing;
        this.lastModificationDate = lastModificationDate;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

    public UUID getShowcaseId() {
        return showcaseId;
    }

    public Integer getPositionOnShowcase() {
        return positionOnShowcase;
    }

    public LocalDate getDateOfPlacing() {
        return dateOfPlacing;
    }

    public LocalDate getLastModificationDate() {
        return lastModificationDate;
    }
}
