package com.depromeet.domain.franchise;


import com.depromeet.domain.franchise.domain.Franchise;

import java.util.List;
import java.util.Optional;

public interface FranchiseRepository {
    List<Franchise> findAll ();
    void save(Franchise franchise);
    Optional<Franchise> findByName(String name);

    Franchise findById(long id);

    void updateImage(Franchise franchise);
}
