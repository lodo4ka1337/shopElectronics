package com.lodo4ka.shopElectronics.persistance.services.impl;

import com.lodo4ka.shopElectronics.exceptions.customExceptions.ShopElectronicsException;
import com.lodo4ka.shopElectronics.exceptions.info.ErrorInfo;
import com.lodo4ka.shopElectronics.exceptions.info.ErrorInfoFactory;
import com.lodo4ka.shopElectronics.persistance.model.DTO.dateValidation.DateValidator;
import com.lodo4ka.shopElectronics.persistance.model.DTO.dateValidation.DateValidatorUsingLocalDate;
import com.lodo4ka.shopElectronics.persistance.model.DTO.mappers.ShowcaseDTOMapper;
import com.lodo4ka.shopElectronics.persistance.model.DTO.ShowcaseDTO;
import com.lodo4ka.shopElectronics.persistance.model.DTO.searchRequests.ShowcaseSearchRequest;
import com.lodo4ka.shopElectronics.persistance.model.entities.QShowcase;
import com.lodo4ka.shopElectronics.persistance.model.entities.Showcase;
import com.lodo4ka.shopElectronics.persistance.repositories.ShowcaseRepository;
import com.lodo4ka.shopElectronics.persistance.services.ShowcaseService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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

        String crDate1 = showcaseSearchRequest.getCreationDate1();
        String crDate2 = showcaseSearchRequest.getCreationDate2();
        String datePattern = DateValidator.ISO_LOCAL_DATE_PATTERN;

        if (crDate1 != null ||
                crDate2 != null) {
            DateValidator dateValidator = new DateValidatorUsingLocalDate();
            java.sql.Date creationDate1 = null;
            java.sql.Date creationDate2 = null;

            if (crDate1 != null) {
                if (dateValidator.isValid(crDate1)) {
                    creationDate1 = java.sql.Date.valueOf(crDate1);
                }
                else {
                    if (crDate2 != null && !dateValidator.isValid(crDate2)) {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("creationDate1", crDate1);
                        parameters.put("creationDate2", crDate2);
                        throwInvalidDateException(datePattern, parameters);
                    }
                    else {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("creationDate1", crDate1);
                        throwInvalidDateException(datePattern, parameters);
                    }
                }
            }

            if (crDate2 != null) {
                if (dateValidator.isValid(crDate2)) {
                    creationDate2 = java.sql.Date.valueOf(crDate2);
                }
                else {
                    if (crDate1 != null && !dateValidator.isValid(crDate1)) {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("creationDate1", crDate1);
                        parameters.put("creationDate2", crDate2);
                        throwInvalidDateException(datePattern, parameters);
                    }
                    else {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("creationDate1", crDate2);
                        throwInvalidDateException(datePattern, parameters);
                    }
                }
            }

            System.err.println(creationDate1);
            System.err.println(creationDate2);
            predicates.and(showcase.creationDate.between(
                    creationDate1,
                    creationDate2));
        }

        /*Date modificationDate1 = showcaseSearchRequest.getModificationDate1();
        Date modificationDate2 = showcaseSearchRequest.getModificationDate2();*/
        Date modificationDate1 = null;
        Date modificationDate2 = null;
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

    private void throwInvalidDateException(String datePattern, Map<String, Object> parameters) {
        ShopElectronicsException exception = new ShopElectronicsException();
        ErrorInfo info = ErrorInfoFactory.getInvalidDateErrorInfo(datePattern, parameters);
        exception.addErrorInfo(info);
        throw exception;
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