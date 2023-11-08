package com.lodo4ka.shopElectronics.persistance.model.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import java.util.UUID;

@StaticMetamodel(Showcase.class)
public abstract class Showcase_ {

    public static volatile SingularAttribute<Showcase, UUID> id;

    public static volatile SingularAttribute<Showcase, String> name;

    public static volatile SingularAttribute<Showcase, String> address;

    public static volatile SingularAttribute<Showcase, String> type;

    public static volatile SingularAttribute<Showcase, LocalDate> creationDate;

    public static volatile SingularAttribute<Showcase, LocalDate> lastUpdate;
}
