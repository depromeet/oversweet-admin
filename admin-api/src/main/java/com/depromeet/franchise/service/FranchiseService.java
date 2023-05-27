package com.depromeet.franchise.service;

import com.depromeet.domain.franchise.FranchiseRepository;
import com.depromeet.domain.franchise.domain.Franchise;
import com.depromeet.domain.franchise.domain.Franchises;
import com.depromeet.franchise.dto.request.CreateFranchiseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FranchiseService {

    private final FranchiseRepository franchiseRepository;

    @Transactional
    public void saveFranchise(final CreateFranchiseRequest request) {
        final Franchises franchises = franchiseRepository.findAll();

        franchises.checkFranchiseDuplicate(request.name());

        final Franchise franchise = Franchise.builder()
                .name(request.name())
                .imageUrl(request.imageUrl())
                .build();

        franchiseRepository.save(franchise);
    }

}
