package com.lodo4ka.shopElectronics.persistance.repositories;

import com.lodo4ka.shopElectronics.persistance.model.entities.Showcase;
import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ShowcaseRepository extends JpaRepository<Showcase, UUID>, QuerydslPredicateExecutor<Showcase> {

    Showcase getShowcaseById(UUID id);

    @NonNull
    List<Showcase> findAll(@NonNull Predicate predicate);

}
