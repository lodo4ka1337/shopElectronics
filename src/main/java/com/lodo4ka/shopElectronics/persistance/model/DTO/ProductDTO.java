package com.lodo4ka.shopElectronics.persistance.model.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.lodo4ka.shopElectronics.persistance.model.DTO.Interfaces.Details;
import com.lodo4ka.shopElectronics.persistance.model.DTO.Interfaces.Exists;
import com.lodo4ka.shopElectronics.persistance.model.DTO.Interfaces.New;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
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

    @Null(groups = {New.class, Exists.class})
    @JsonView({Details.class})
    private final Date dateOfPlacing;

    @Null(groups = {New.class, Exists.class})
    @JsonView({Details.class})
    private final Date lastModificationDate;

    @JsonCreator
    public ProductDTO(@JsonProperty("id") UUID id,
                      @JsonProperty("name") String name,
                      @JsonProperty("type") String type,
                      @JsonProperty("price") Double price,
                      @JsonProperty("showcaseId") UUID showcaseId,
                      @JsonProperty("positionOnShowcase") Integer positionOnShowcase,
                      @JsonProperty("dateOfPlacing")Date dateOfPlacing,
                      @JsonProperty("lastModificationDate")Date lastModificationDate) {
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

    public Date getDateOfPlacing() {
        return dateOfPlacing;
    }

    public Date getLastModificationDate() {
        return lastModificationDate;
    }
}
