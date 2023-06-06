package com.depromeet.domain.drink.entity;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DrinkEntityRepository extends JpaRepository<DrinkEntity, Long> {
    Optional<DrinkEntity> findByName(String name);

    List<DrinkEntity> findAllByFranchiseId(Long franchiseId, PageRequest pageRequest);

    Optional<DrinkEntity> findByFranchiseIdAndName(Long franchiseId, String name);
}
