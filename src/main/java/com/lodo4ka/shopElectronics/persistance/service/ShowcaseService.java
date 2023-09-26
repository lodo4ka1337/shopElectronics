package com.lodo4ka.shopElectronics.persistance.service;

import com.lodo4ka.shopElectronics.persistance.model.Showcase;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShowcaseService {

    List<Showcase> getShowcases(String type, String address, Date crDate1, Date crDate2, Date actDate1, Date actDate2);

    void addShowcase(Showcase showcase);

    void deleteShowcaseById(UUID id);

    void actualizeShowcaseById(UUID id);

    void updateShowcase(UUID id, Optional<String> name, Optional<String> address, Optional<String> type);
}
