package com.lodo4ka.shopElectronics.persistance.service;

import com.lodo4ka.shopElectronics.persistance.model.Showcase;
import com.lodo4ka.shopElectronics.persistance.repository.ShowcaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ShowcaseServiceImpl implements ShowcaseService {

    private ShowcaseRepository showcaseRepository;

    @Autowired
    public ShowcaseServiceImpl(ShowcaseRepository showcaseRepository) {
        this.showcaseRepository = showcaseRepository;
    }

    @Override
    public List<Showcase> getAllShowcases() {
        return showcaseRepository.findAll();
    }

    @Override
    @Transactional
    public void addShowcase(Showcase showcase) {
        actualizeShowcase(showcase);
        showcaseRepository.save(showcase);
    }

    @Override
    @Transactional
    public void deleteShowcaseById(UUID id) {
        showcaseRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void updateShowcase(Showcase showcase) {
        actualizeShowcase(showcase);
        showcaseRepository.save(showcase);
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

    @Transactional
    @Override
    public void actualizeShowcase(Showcase showcase) {
        showcase.setLastUpdate(new Date(new java.util.Date().getTime()));
    }

}
