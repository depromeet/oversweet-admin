package com.depromeet.domain.franchise.infrastructure.persistence;


import com.depromeet.domain.franchise.domain.Franchise;

import java.util.List;
import java.util.Optional;

public interface FranchiseRepository {
    List<Franchise> findAll ();

    void save(Franchise franchise);

    Optional<Franchise> findByName(String name);

    Franchise findById(Long id);

    void modifyImageUrl(Franchise franchise);
}
