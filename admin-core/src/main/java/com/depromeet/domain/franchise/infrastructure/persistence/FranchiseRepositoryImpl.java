package com.depromeet.domain.franchise.infrastructure.persistence;

import com.depromeet.common.exception.franchise.FranchiseNotFoundException;
import com.depromeet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.domain.franchise.entity.FranchiseEntityRepository;
import com.depromeet.domain.franchise.FranchiseRepository;
import com.depromeet.domain.franchise.domain.Franchise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FranchiseRepositoryImpl implements FranchiseRepository {
    private final FranchiseEntityRepository franchiseEntityRepository;

    @Override
    public List<Franchise> findAll() {
        final var franchiseEntities = franchiseEntityRepository.findAll();

        return franchiseEntities.stream()
                .map(FranchiseEntity::toDomain)
                .toList();
    }

    @Override
    public void save(final Franchise franchise) {
        final var franchiseEntity = FranchiseEntity.builder()
                .name(franchise.getName())
                .imageUrl(franchise.getImageUrl())
                .build();
        franchiseEntityRepository.save(franchiseEntity);
    }

    @Override
    public Optional<Franchise> findByName(final String name) {
        final var findFranchiseEntity = franchiseEntityRepository.findByName(name);
        return findFranchiseEntity.map(FranchiseEntity::toDomain);
    }

    @Override
    public Franchise findById(final Long id) {
        final var findFranchiseEntity = franchiseEntityRepository.findById(id)
                 .orElseThrow(FranchiseNotFoundException::new);
        return findFranchiseEntity.toDomain();
    }

    @Override
    public void modifyImageUrl(final Franchise franchise) {
        final var findFranchiseEntity = franchiseEntityRepository.findById(franchise.getId())
                .orElseThrow(FranchiseNotFoundException::new);
        findFranchiseEntity.modifyImageUrl(franchise.getImageUrl());
    }
}
