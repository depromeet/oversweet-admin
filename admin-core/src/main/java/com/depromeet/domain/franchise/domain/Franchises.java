package com.depromeet.domain.franchise.domain;


import com.depromeet.common.exception.franchise.FranchiseAlreadyExistException;
import lombok.Getter;

import java.util.List;

@Getter
public class Franchises {
    private final List<Franchise> franchises;

    public Franchises(final List<Franchise> franchises) {
        this.franchises = franchises;
    }

    public void checkFranchiseDuplicate(final String name){
        if (franchises.stream().anyMatch(franchise -> franchise.isSameName(name))) {
            throw new FranchiseAlreadyExistException();
        }
    }
}
