package com.lodo4ka.shopElectronics.persistance.services.impl;

import com.lodo4ka.shopElectronics.exceptions.customExceptions.ShopElectronicsException;
import com.lodo4ka.shopElectronics.exceptions.info.ErrorInfo;
import com.lodo4ka.shopElectronics.exceptions.info.ErrorInfoFactory;
import com.lodo4ka.shopElectronics.exceptions.info.utils.ErrorType;
import com.lodo4ka.shopElectronics.persistance.model.DTO.dateValidation.DateValidator;
import com.lodo4ka.shopElectronics.persistance.model.DTO.dateValidation.DateValidatorUsingLocalDate;
import com.lodo4ka.shopElectronics.persistance.model.DTO.mappers.ShowcaseDTOMapper;
import com.lodo4ka.shopElectronics.persistance.model.DTO.ShowcaseDTO;
import com.lodo4ka.shopElectronics.persistance.model.DTO.searchRequests.ShowcaseSearchRequest;
import com.lodo4ka.shopElectronics.persistance.model.entities.Showcase;
import com.lodo4ka.shopElectronics.persistance.model.entities.Showcase_;
import com.lodo4ka.shopElectronics.persistance.repositories.ShowcaseRepository;
import com.lodo4ka.shopElectronics.persistance.services.ShowcaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ShowcaseServiceImpl implements ShowcaseService {

    String datePattern = DateValidator.ISO_LOCAL_DATE_PATTERN;

    DateValidator dateValidator = new DateValidatorUsingLocalDate();

    private final ShowcaseRepository showcaseRepository;

    private final ShowcaseDTOMapper showcaseDTOMapper;

    private final EntityManager em;

    @Autowired
    public ShowcaseServiceImpl(ShowcaseRepository showcaseRepository, ShowcaseDTOMapper showcaseDTOMapper, EntityManager em) {
        this.showcaseRepository = showcaseRepository;
        this.showcaseDTOMapper = showcaseDTOMapper;
        this.em = em;
    }

    @Override
    public List<ShowcaseDTO> getShowcases(ShowcaseSearchRequest showcaseSearchRequest) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Showcase> criteriaQuery = criteriaBuilder.createQuery(Showcase.class);
        Root<Showcase> root = criteriaQuery.from(Showcase.class);

        List<Predicate> predicates = new ArrayList<>();

        if (showcaseSearchRequest.getType() != null) {
            Predicate typePredicate = criteriaBuilder
                    .equal(root.get(Showcase_.type), showcaseSearchRequest.getType());
            predicates.add(typePredicate);
        }

        if (showcaseSearchRequest.getAddress() != null) {
            Predicate addressPredicate = criteriaBuilder
                    .equal(root.get(Showcase_.address), showcaseSearchRequest.getAddress());
            predicates.add(addressPredicate);
        }

        String crDate1 = showcaseSearchRequest.getCreatedAfter();
        String crDate2 = showcaseSearchRequest.getCreatedBefore();
        if (crDate1 != null || crDate2 != null)
            if (crDate1 != null && crDate2 != null)
                createDateBetweenPredicate(
                        crDate1,
                        crDate2,
                        criteriaBuilder,
                        root,
                        predicates,
                        Showcase_.creationDate,
                        "createdAfter",
                        "createdBefore");

        String modDate1 = showcaseSearchRequest.getUpdatedAfter();
        String modDate2 = showcaseSearchRequest.getUpdateBefore();
        if (modDate1 != null || modDate2 != null)
            if (modDate1 != null && modDate2 != null)
                createDateBetweenPredicate(
                        modDate1,
                        modDate2,
                        criteriaBuilder,
                        root,
                        predicates,
                        Showcase_.lastUpdate,
                        "updatedAfter",
                        "updatedBefore");

        criteriaQuery.where(
                criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        TypedQuery<Showcase> query = em.createQuery(criteriaQuery);
        return query.getResultList().stream()
                .map(showcaseDTOMapper)
                .collect(Collectors.toList());
    }

    private void createDateBetweenPredicate(String date1,
                                            String date2,
                                            CriteriaBuilder criteriaBuilder,
                                            Root<Showcase> root,
                                            List<Predicate> predicates,
                                            SingularAttribute<Showcase, LocalDate> date,
                                            String date1Alias,
                                            String date2Alias) {

        if (date1 != null && date2 != null) {
            boolean date1isValid = true;
            boolean date2isValid = true;
            LocalDate localDate1 = null;
            LocalDate localDate2 = null;

            if (dateValidator.isValid(date1))
                localDate1 = LocalDate.parse(date1);
            else
                date1isValid = false;

            if (dateValidator.isValid(date2)) localDate2 = LocalDate.parse(date2);
            else date2isValid = false;

            if (date1isValid && date2isValid) {
                Predicate dateBetweenPredicate = criteriaBuilder
                        .between(root.get(date), localDate1, localDate2);
                predicates.add(dateBetweenPredicate);
            }
            else {
                Map<String, Object> parameters = new HashMap<>();
                if (!date1isValid) parameters.put(date1Alias, date1);
                if (!date2isValid) parameters.put(date2Alias, date2);
                throwInvalidDateException(datePattern, parameters);
            }
        }
    }

    private void throwInvalidDateException(String datePattern, Map<String, Object> parameters) {
        ShopElectronicsException exception = new ShopElectronicsException();
        exception.setErrorStatus(HttpStatus.BAD_REQUEST);
        exception.setErrorType(ErrorType.CLIENT);
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