package com.lodo4ka.shopElectronics.persistance.repository;

import com.lodo4ka.shopElectronics.persistance.model.Showcase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface ShowcaseRepository extends JpaRepository<Showcase, UUID> {

    Showcase getShowcaseById(UUID id);
    List<Showcase> getAllShowcasesByType(String type);

    List<Showcase> getAllShowcasesByAddress(String address);

    List<Showcase> getShowcasesByCreationDateBetween(Date date1, Date date2);

    List<Showcase> getShowcasesByLastUpdateBetween(Date date1, Date date2);

}
