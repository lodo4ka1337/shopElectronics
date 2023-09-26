package com.lodo4ka.shopElectronics.persistance.service.impl;

import com.lodo4ka.shopElectronics.persistance.model.QShowcase;
import com.lodo4ka.shopElectronics.persistance.model.Showcase;
import com.lodo4ka.shopElectronics.persistance.repository.ShowcaseRepository;
import com.lodo4ka.shopElectronics.persistance.service.ShowcaseService;
import com.querydsl.core.BooleanBuilder;
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
    public List<Showcase> getShowcases(String type, String address, Date crDate1, Date crDate2, Date actDate1, Date actDate2) {
        QShowcase showcase = QShowcase.showcase;
        BooleanBuilder predicates = new BooleanBuilder();

        if (type != null) {
            predicates.and(showcase.type.eq(type));
        }

        if (address != null) {
            predicates.and(showcase.address.eq(address));
        }

        if (crDate1 != null || crDate2 != null) {
            if (crDate1 != null && crDate2 != null) {
                if (crDate1.before(crDate2)) {
                    predicates.and(showcase.creationDate.between(crDate1, crDate2));
                }
                else {

                }
            }
            else {

            }
        }

        if (actDate1 != null || actDate2 != null) {
            if (actDate1 != null && actDate2 != null) {
                if (actDate1.before(actDate2)) {
                    predicates.and(showcase.lastUpdate.between(actDate1, actDate2));
                } else {

                }
            }
            else {

            }
        }

        return showcaseRepository.findAll(predicates);
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
