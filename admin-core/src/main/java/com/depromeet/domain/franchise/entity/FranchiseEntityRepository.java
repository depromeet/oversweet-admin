package com.depromeet.domain.franchise.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface FranchiseEntityRepository extends JpaRepository<FranchiseEntity, Long> {
    Optional<FranchiseEntity> findByName(String name);
}
