package com.lodo4ka.shopElectronics.persistance.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

import java.util.Date;

import java.util.UUID;

@Entity
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
public class Product implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name",  nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "showcase_id", nullable = false)
    private UUID showcaseId;

    @Column(name = "position_on_showcase", nullable = false)
    private int positionOnShowcase;

    @Column(name = "date_of_placing", updatable = false)
    @CreatedDate
    private Date dateOfPlacing;

    @Column(name = "last_modification_date")
    @LastModifiedDate
    private Date lastUpdate;

    public Product() {
    }

    public Product(String name, String type, double price, UUID showcaseId, int positionOnShowcase) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.showcaseId = showcaseId;
        this.positionOnShowcase = positionOnShowcase;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public UUID getShowcaseId() {
        return showcaseId;
    }

    public void setShowcaseId(UUID showcase_id) {
        this.showcaseId = showcase_id;
    }

    public int getPositionOnShowcase() {
        return positionOnShowcase;
    }

    public void setPositionOnShowcase(int position_on_showcase) {
        this.positionOnShowcase = position_on_showcase;
    }

    public Date getDateOfPlacing() {
        return dateOfPlacing;
    }

    public void setDateOfPlacing(Date date_of_placing) {
        this.dateOfPlacing = date_of_placing;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date last_update_date) {
        this.lastUpdate = last_update_date;
    }

}
