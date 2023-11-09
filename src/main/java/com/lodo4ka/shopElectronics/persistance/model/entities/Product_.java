package com.lodo4ka.shopElectronics.persistance.model.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import java.util.UUID;

@StaticMetamodel(Product_.class)
public class Product_ {
    public static volatile SingularAttribute<Product, UUID> id;

    public static volatile SingularAttribute<Product, String> name;

    public static volatile SingularAttribute<Product, String> type;

    public static volatile SingularAttribute<Product, Double> price;

    public static volatile SingularAttribute<Product, UUID> showcaseId;

    public static volatile SingularAttribute<Product, Integer> positionOnShowcase;

    public static volatile SingularAttribute<Product, LocalDate> dateOfPlacing;

    public static volatile SingularAttribute<Product, LocalDate> lastUpdate;
}
