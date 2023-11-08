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

public class ShowcaseDTO {

    @Null(groups = {New.class})
    @NotNull(groups = {Exists.class})
    @JsonView({Details.class})
    private final UUID id;

    @NotNull(groups = {New.class})
    @JsonView({Details.class})
    private final String name;

    @NotNull(groups = {New.class})
    @JsonView({Details.class})
    private final String address;

    @NotNull(groups = {New.class})
    @JsonView({Details.class})
    private final String type;

    @JsonView({Details.class})
    private final LocalDate creationDate;

    @JsonView({Details.class})
    private final LocalDate lastUpdate;

    @JsonCreator
    public ShowcaseDTO(@JsonProperty("id") UUID id,
                       @JsonProperty("name") String name,
                       @JsonProperty("address") String address,
                       @JsonProperty("type") String type,
                       @JsonProperty("creationDate") LocalDate creationDate,
                       @JsonProperty("lastUpdate") LocalDate lastUpdate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.type = type;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getType() {
        return type;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }
}
