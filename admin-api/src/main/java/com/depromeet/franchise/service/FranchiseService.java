package com.depromeet.franchise.service;

import com.depromeet.common.exception.franchise.FranchiseAlreadyExistException;
import com.depromeet.domain.franchise.FranchiseRepository;
import com.depromeet.domain.franchise.domain.Franchise;
import com.depromeet.franchise.dto.request.CreateFranchiseRequest;
import com.depromeet.franchise.dto.request.ModifyFranchiseImageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FranchiseService {

    private final FranchiseRepository franchiseRepository;

    @Transactional
    public void saveFranchise(final CreateFranchiseRequest request) {
        validateDuplicatedFranchiseName(request.name());

        final var franchise = Franchise.builder()
                .name(request.name())
                .imageUrl(request.imageUrl())
                .build();

        franchiseRepository.save(franchise);
    }

    @Transactional
    public void modifyFranchiseImage(final Long id, final ModifyFranchiseImageRequest request) {
         final var franchise = franchiseRepository.findById(id);
         franchise.modifyImageUrl(request.imageUrl());
         franchiseRepository.updateImage(franchise);
    }

    private void validateDuplicatedFranchiseName(final String name){
        franchiseRepository.findByName(name).ifPresent(
                findFranchise -> {
                    if (findFranchise.isSameName(name)) {
                        throw new FranchiseAlreadyExistException();
                    }
                }
        );
    }
}
