package com.lodo4ka.shopElectronics.persistance.service;

import com.lodo4ka.shopElectronics.persistance.model.Showcase;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShowcaseService {
    List<Showcase> getAllShowcases();

    void addShowcase(Showcase showcase);

    void deleteShowcaseById(UUID id);

    List<Showcase> getAllShowcasesFilteredByType(String type);

    List<Showcase> getAllShowcasesFilteredByAddress(String address);

    List<Showcase> getAllShowcasesCreatedBetween(Date date1, Date date2);

    List<Showcase> getAllShowcasesActualizedBetween(Date date1, Date date2);

    void actualizeShowcaseById(UUID id);

    void updateShowcase(UUID id, Optional<String> name, Optional<String> address, Optional<String> type, Optional<Date> creationDate);
}
