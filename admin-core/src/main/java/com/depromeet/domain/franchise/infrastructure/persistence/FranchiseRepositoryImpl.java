package com.depromeet.domain.franchise.infrastructure.persistence;

import com.depromeet.common.exception.franchise.FranchiseNotFoundException;
import com.depromeet.domain.franchise.domain.Franchises;
import com.depromeet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.domain.franchise.entity.FranchiseEntityRepository;
import com.depromeet.domain.franchise.FranchiseRepository;
import com.depromeet.domain.franchise.domain.Franchise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FranchiseRepositoryImpl implements FranchiseRepository {
    private final FranchiseEntityRepository franchiseEntityRepository;

    @Override
    public Franchises findAll() {
        final List<FranchiseEntity> franchiseEntities = franchiseEntityRepository.findAll();

        final List<Franchise> franchises = franchiseEntities.stream()
                .map(FranchiseEntity::toDomain)
                .toList();

        return new Franchises(franchises);
    }

    @Override
    public void save(final Franchise franchise) {
        final FranchiseEntity franchiseEntity = Franchise.toEntity(franchise);
        franchiseEntityRepository.save(franchiseEntity);
    }
}
