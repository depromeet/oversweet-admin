package com.depromeet.domain.drink.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DrinkEntityRepository extends JpaRepository<DrinkEntity, Long> {
    Optional<DrinkEntity> findByNameAndFranchiseId(String name, Long franchiseId);
}
