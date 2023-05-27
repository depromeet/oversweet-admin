package com.depromeet.domain.franchise;


import com.depromeet.domain.franchise.domain.Franchise;
import com.depromeet.domain.franchise.domain.Franchises;

public interface FranchiseRepository {
    Franchises findAll ();
    void save(Franchise franchise);
}
