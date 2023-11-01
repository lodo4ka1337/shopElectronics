package com.lodo4ka.shopElectronics.persistance.model.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.lodo4ka.shopElectronics.persistance.model.DTO.interfaces.Details;
import com.lodo4ka.shopElectronics.persistance.model.DTO.interfaces.Exists;
import com.lodo4ka.shopElectronics.persistance.model.DTO.interfaces.New;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
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

    @Null(groups = {New.class, Exists.class})
    @JsonView({Details.class})
    private final Date creationDate;

    @Null(groups = {New.class, Exists.class})
    @JsonView({Details.class})
    private final Date lastModificationDate;

    @JsonCreator
    public ShowcaseDTO(@JsonProperty("id") UUID id,
                       @JsonProperty("name") String name,
                       @JsonProperty("address") String address,
                       @JsonProperty("type") String type,
                       @JsonProperty("creationDate") Date creationDate,
                       @JsonProperty("lastModificationDate") Date lastModificationDate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.type = type;
        this.creationDate = creationDate;
        this.lastModificationDate = lastModificationDate;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastModificationDate() {
        return lastModificationDate;
    }
}
