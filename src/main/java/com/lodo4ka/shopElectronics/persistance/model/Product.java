package com.lodo4ka.shopElectronics.persistance.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

import java.security.Timestamp;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "products")
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
    private UUID showcase_id;

    @Column(name = "position_on_showcase", unique = true ,nullable = false)
    private int position_on_showcase;

    @Column(name = "date_of_placing")
    private Timestamp date_of_placing;

    @Column(name = "last_update_date")
    private Timestamp last_update_date;

    public Product() {
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

    public UUID getShowcase_id() {
        return showcase_id;
    }

    public void setShowcase_id(UUID showcase_id) {
        this.showcase_id = showcase_id;
    }

    public int getPosition_on_showcase() {
        return position_on_showcase;
    }

    public void setPosition_on_showcase(int position_on_showcase) {
        this.position_on_showcase = position_on_showcase;
    }

    public Timestamp getDate_of_placing() {
        return date_of_placing;
    }

    public void setDate_of_placing(Timestamp date_of_placing) {
        this.date_of_placing = date_of_placing;
    }

    public Timestamp getLast_update_date() {
        return last_update_date;
    }

    public void setLast_update_date(Timestamp last_update_date) {
        this.last_update_date = last_update_date;
    }

}
