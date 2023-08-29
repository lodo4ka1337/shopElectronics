package com.lodo4ka.shopElectronics.persistance.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

import java.sql.Date;

import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "showcases")
public class Showcase implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", unique = true, nullable = false)
    private String address;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "last_update_date", insertable = false)
    private Date lastUpdate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "showcase_id", updatable = false)
    private List<Product> products;

    public Showcase() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creation_date) {
        this.creationDate = creation_date;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date last_update_date) {
        this.lastUpdate = last_update_date;
    }
}
