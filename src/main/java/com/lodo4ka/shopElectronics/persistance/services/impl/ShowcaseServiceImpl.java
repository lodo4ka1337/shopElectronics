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

        if (showcaseSearchRequest.getType() != null)
            predicates.add(
                criteriaBuilder.equal(
                        root.get(Showcase_.type), showcaseSearchRequest.getType()));

        if (showcaseSearchRequest.getAddress() != null)
            predicates.add(
                    criteriaBuilder.equal(
                            root.get(Showcase_.address), showcaseSearchRequest.getAddress()));

        String crDate1 = showcaseSearchRequest.getCreatedAfter();
        String crDate2 = showcaseSearchRequest.getCreatedBefore();
        if (crDate1 != null || crDate2 != null)
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

    private void createDateBetweenPredicate(String dateAfter,
                                            String dateBefore,
                                            CriteriaBuilder criteriaBuilder,
                                            Root<Showcase> root,
                                            List<Predicate> predicates,
                                            SingularAttribute<Showcase, LocalDate> date,
                                            String dateAfterAlias,
                                            String dateBeforeAlias) {

        boolean dateAfterIsValid = true;
        boolean dateBeforeIsValid = true;
        LocalDate localDateAfter;
        LocalDate localDateBefore;

        if (dateAfter != null) {
            if (dateValidator.isValid(dateAfter)) {
                localDateAfter = LocalDate.parse(dateAfter);
                predicates.add(
                        criteriaBuilder.greaterThanOrEqualTo(
                                root.get(date), localDateAfter));
            }
            else dateAfterIsValid = false;
        }

        if (dateBefore != null) {
            if (dateValidator.isValid(dateBefore)) {
                localDateBefore = LocalDate.parse(dateBefore);
                predicates.add(
                        criteriaBuilder.lessThanOrEqualTo(
                                root.get(date), localDateBefore));
            }
            else dateBeforeIsValid = false;
        }

        if (!dateAfterIsValid || !dateBeforeIsValid) {
            Map<String, Object> parameters = new HashMap<>();
            if (!dateAfterIsValid) parameters.put(dateAfterAlias, dateAfter);
            if (!dateBeforeIsValid) parameters.put(dateBeforeAlias, dateBefore);

            ShopElectronicsException exception = new ShopElectronicsException();
            exception.setErrorStatus(HttpStatus.BAD_REQUEST);
            exception.setErrorType(ErrorType.CLIENT);
            ErrorInfo info = ErrorInfoFactory.getInvalidDateErrorInfo(datePattern, parameters);
            exception.addErrorInfo(info);
            throw exception;
        }
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