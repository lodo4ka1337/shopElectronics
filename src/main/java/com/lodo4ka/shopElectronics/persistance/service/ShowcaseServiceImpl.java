package com.lodo4ka.shopElectronics.persistance.service;

import com.lodo4ka.shopElectronics.persistance.model.Showcase;
import com.lodo4ka.shopElectronics.persistance.repository.ShowcaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ShowcaseServiceImpl implements ShowcaseService {

    private final ShowcaseRepository showcaseRepository;

    @Autowired
    public ShowcaseServiceImpl(ShowcaseRepository showcaseRepository) {
        this.showcaseRepository = showcaseRepository;
    }

    @Override
    public List<Showcase> getAllShowcases() {
        return showcaseRepository.findAll();
    }

    @Override
    public List<Showcase> getAllShowcasesFilteredByType(String type) {
        return showcaseRepository.getAllShowcasesByType(type);
    }

    @Override
    public List<Showcase> getAllShowcasesFilteredByAddress(String address) {
        return showcaseRepository.getAllShowcasesByAddress(address);
    }

    @Override
    public List<Showcase> getAllShowcasesCreatedBetween(Date date1, Date date2) {
        return showcaseRepository.getShowcasesByCreationDateBetween(date1, date2);
    }

    @Override
    public List<Showcase> getAllShowcasesActualizedBetween(Date date1, Date date2) {
        return showcaseRepository.getShowcasesByLastUpdateBetween(date1, date2);
    }

    @Override
    @Transactional
    public void addShowcase(Showcase showcase) {
        showcaseRepository.save(showcase);
        showcaseRepository.getShowcaseById(showcase.getId()).
                setCreationDate(new Date(new java.util.Date().getTime()));
        actualizeShowcaseById(showcase.getId());
    }

    @Override
    @Transactional
    public void deleteShowcaseById(UUID id) {
        showcaseRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void updateShowcase(UUID id, Optional<String> name, Optional<String> address, Optional<String> type) {
        Showcase showcase = showcaseRepository.getShowcaseById(id);
        name.ifPresent(showcase::setName);
        address.ifPresent(showcase::setAddress);
        type.ifPresent(showcase::setType);
        actualizeShowcaseById(id);
    }

    @Transactional
    @Override
    public void actualizeShowcaseById(UUID id) {
        Showcase showcase = showcaseRepository.getShowcaseById(id);
        showcase.setLastUpdate(new Date(new java.util.Date().getTime()));
        showcaseRepository.save(showcase);
    }

}
