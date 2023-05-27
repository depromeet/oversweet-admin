package com.depromeet.domain.franchise.infrastructure.persistence;

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
        final List<FranchiseEntity> franchiseEntities = franchiseEntityRepository.findAll();

        return franchiseEntities.stream()
                .map(FranchiseEntity::toDomain)
                .toList();
    }

    @Override
    public void save(final Franchise franchise) {
        final FranchiseEntity franchiseEntity = Franchise.toEntity(franchise);
        franchiseEntityRepository.save(franchiseEntity);
    }

    @Override
    public Optional<Franchise> findByName(final String name) {
        final Optional<FranchiseEntity> findFranchiseEntity = franchiseEntityRepository.findByName(name);
        return findFranchiseEntity.map(FranchiseEntity::toDomain);
    }

    @Override
    public Optional<Franchise> findById(long id) {
        final Optional<FranchiseEntity> findFranchiseEntity = franchiseEntityRepository.findById(id);
        return findFranchiseEntity.map(FranchiseEntity::toDomain);
    }
}
