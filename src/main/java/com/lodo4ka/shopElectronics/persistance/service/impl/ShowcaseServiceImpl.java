package com.lodo4ka.shopElectronics.persistance.service.impl;

import com.lodo4ka.shopElectronics.persistance.model.DTO.Mapper.ShowcaseDTOMapper;
import com.lodo4ka.shopElectronics.persistance.model.DTO.ShowcaseDTO;
import com.lodo4ka.shopElectronics.persistance.model.DTO.ShowcaseSearchRequest;
import com.lodo4ka.shopElectronics.persistance.model.QShowcase;
import com.lodo4ka.shopElectronics.persistance.model.Showcase;
import com.lodo4ka.shopElectronics.persistance.repository.ShowcaseRepository;
import com.lodo4ka.shopElectronics.persistance.service.ShowcaseService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ShowcaseServiceImpl implements ShowcaseService {

    private final ShowcaseRepository showcaseRepository;

    private final ShowcaseDTOMapper showcaseDTOMapper;

    @Autowired
    public ShowcaseServiceImpl(ShowcaseRepository showcaseRepository, ShowcaseDTOMapper showcaseDTOMapper) {
        this.showcaseRepository = showcaseRepository;
        this.showcaseDTOMapper = showcaseDTOMapper;
    }

    @Override
    public List<ShowcaseDTO> getShowcases(ShowcaseSearchRequest showcaseSearchRequest) {
        QShowcase showcase = QShowcase.showcase;
        BooleanBuilder predicates = new BooleanBuilder();

        if (showcaseSearchRequest.getType() != null) {
            predicates.and(showcase.type.eq(showcaseSearchRequest.getType()));
        }

        if (showcaseSearchRequest.getAddress() != null) {
            predicates.and(showcase.address.eq(showcaseSearchRequest.getAddress()));
        }

        Date creationDate1 = showcaseSearchRequest.getCreationDate1();
        Date creationDate2 = showcaseSearchRequest.getCreationDate2();
        if (creationDate1 != null
                || creationDate2 != null) {
            if (creationDate1 != null
                    && creationDate2 != null) {
                if (creationDate1.before(creationDate2)) {
                    predicates.and(showcase.creationDate.between(
                            creationDate1,
                            creationDate2));
                }
                else {

                }
            }
            else {

            }
        }

        Date modificationDate1 = showcaseSearchRequest.getModificationDate1();
        Date modificationDate2 = showcaseSearchRequest.getModificationDate2();
        if (modificationDate1 != null
                || modificationDate2 != null) {
            if (modificationDate1 != null
                    && modificationDate2 != null) {
                if (modificationDate1.before(modificationDate2)) {
                    predicates.and(showcase.lastUpdate.between(
                            modificationDate1,
                            modificationDate2));
                } else {

                }
            }
            else {

            }
        }

        return showcaseRepository.findAll(predicates)
                .stream()
                .map(showcaseDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ShowcaseDTO addShowcase(ShowcaseDTO showcaseAddRequest) {
        Showcase showcase = new Showcase(
                showcaseAddRequest.getName(),
                showcaseAddRequest.getAddress(),
                showcaseAddRequest.getType()
        );
        showcaseRepository.save(showcase);

        return new ShowcaseDTO(
                showcase.getId(),
                showcase.getName(),
                showcase.getAddress(),
                showcase.getType(),
                showcase.getCreationDate(),
                showcase.getLastUpdate()
        );
    }

    @Transactional
    @Override
    public ShowcaseDTO updateShowcase(ShowcaseDTO showcaseUpdateRequest) {
        Showcase showcase = showcaseRepository.getShowcaseById(showcaseUpdateRequest.getId());

        if (showcaseUpdateRequest.getName() != null) {
            showcase.setName(showcaseUpdateRequest.getName());
        }

        if (showcaseUpdateRequest.getAddress() != null) {
            showcase.setAddress(showcase.getAddress());
        }

        if (showcaseUpdateRequest.getType() != null) {
            showcase.setType(showcaseUpdateRequest.getType());
        }

        showcaseRepository.save(showcase);

        return new ShowcaseDTO(
                showcase.getId(),
                showcase.getName(),
                showcase.getAddress(),
                showcase.getType(),
                showcase.getCreationDate(),
                showcase.getLastUpdate()
        );
    }

    @Override
    @Transactional
    public void deleteShowcaseById(UUID id) {
        showcaseRepository.deleteById(id);
    }
}