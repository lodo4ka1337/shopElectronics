package com.lodo4ka.shopElectronics.persistance.model.entities;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "showcases")
@EntityListeners(AuditingEntityListener.class)
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

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "creation_date", updatable = false)
    @CreatedDate
    private Date creationDate;

    @Column(name = "last_modification_date")
    @LastModifiedDate
    private Date lastUpdate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "showcase_id")
    private List<Product> products;

    public Showcase() {
    }

    public Showcase(String name, String address, String type) {
        this.name = name;
        this.address = address;
        this.type = type;
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

    public java.util.Date getCreationDate() {
        return creationDate;
    }

    public java.util.Date getLastUpdate() {
        return lastUpdate;
    }
}
