package com.depromeet.franchise.service;


import com.depromeet.common.exception.franchise.FranchiseAlreadyExistException;
import com.depromeet.domain.franchise.FranchiseRepository;
import com.depromeet.domain.franchise.domain.Franchise;
import com.depromeet.domain.franchise.domain.Franchises;
import com.depromeet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.franchise.dto.request.CreateFranchiseRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.misusing.FriendlyReminderException;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

@DisplayName("프랜차이즈 서비스 테스트")
@MockitoSettings
class FranchiseEntityServiceTest {

    @Mock
    private FranchiseRepository franchiseRepository;

    @InjectMocks
    FranchiseService franchiseService;

    @Test
    @DisplayName("프랜차이즈 - 성공")
    void saveFranchise() {
        CreateFranchiseRequest request = franchiseRequest();
        Franchises existingFranchises = new Franchises(franchises(franchise()));
        Mockito.when(franchiseRepository.findAll()).thenReturn(existingFranchises);

        franchiseService.saveFranchise(request);

        Mockito.verify(franchiseRepository).save(Mockito.any());
    }


    @Test
    @DisplayName("프랜차이즈 - 중복으로인한 실패")
    void saveFranchiseFailedWhenDuplicateFranchiseName() {
        CreateFranchiseRequest request = franchiseRequest();
        Franchises existingFranchises = new Franchises(franchises(duplicateNameFranchise()));
        Mockito.when(franchiseRepository.findAll()).thenReturn(existingFranchises);

        assertThatThrownBy(() -> franchiseService.saveFranchise(request))
                .isInstanceOf(FranchiseAlreadyExistException.class);
    }

    private CreateFranchiseRequest franchiseRequest() {
        return CreateFranchiseRequest.builder()
                .name("test")
                .imageUrl("test")
                .build();
    }

    private FranchiseEntity franchiseEntity(long id) {
        return FranchiseEntity.builder()
                .id(id)
                .name("test")
                .imageUrl("test")
                .build();
    }
    private Franchise franchise() {
        return Franchise.builder()
                .name("test2")
                .imageUrl("test2")
                .build();
    }
    private Franchise duplicateNameFranchise() {
        return Franchise.builder()
                .name("test")
                .imageUrl("test")
                .build();
    }

    private List<Franchise> franchises(Franchise franchise) {
        List<Franchise> franchises = new ArrayList<>();
        franchises.add(franchise);
        return franchises;
    }

}