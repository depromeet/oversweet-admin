package com.depromeet.domain.drink.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DrinkEntityRepository extends JpaRepository<DrinkEntity, Long> {
    Optional<DrinkEntity> findByName(String name);

    Page<DrinkEntity> findAllByFranchiseId(Long franchiseId, Pageable pageRequest);

    Optional<DrinkEntity> findByFranchiseIdAndName(Long franchiseId, String name);
}
