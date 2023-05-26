package com.depromeet.franchise.service;

import com.depromeet.domain.franchise.Franchise;
import com.depromeet.domain.franchise.FranchiseRepository;
import com.depromeet.franchise.dto.request.CreateFranchiseRequest;
import com.depromeet.common.exception.franchise.FranchiseAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FranchiseService {

    private final FranchiseRepository franchiseRepository;

    @Transactional
    public void saveFranchise(final CreateFranchiseRequest request){
        validateDuplicatedFranchiseName(request.getName());
        final Franchise franchise = request.toFranchise();
        franchiseRepository.save(franchise);
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
