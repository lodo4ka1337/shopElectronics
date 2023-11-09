package com.lodo4ka.shopElectronics.persistance.repositories;

import com.lodo4ka.shopElectronics.persistance.model.entities.Showcase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShowcaseRepository extends JpaRepository<Showcase, UUID> {

    Showcase getShowcaseById(UUID id);

}
