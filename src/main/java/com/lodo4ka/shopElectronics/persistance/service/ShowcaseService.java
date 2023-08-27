package com.lodo4ka.shopElectronics.persistance.service;

import com.lodo4ka.shopElectronics.persistance.model.Showcase;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShowcaseService {
    List<Showcase> getAllShowcases();

    Optional<Showcase> getShowcaseById(UUID id);

    void addShowcase(Showcase showcase);
}
