package com.depromeet.franchise.service;


import com.depromeet.common.exception.franchise.FranchiseAlreadyExistException;
import com.depromeet.domain.franchise.FranchiseRepository;
import com.depromeet.domain.franchise.domain.Franchise;
import com.depromeet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.franchise.dto.request.CreateFranchiseRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.misusing.FriendlyReminderException;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
        // given
        CreateFranchiseRequest request = franchiseRequest();
        Franchise franchise = franchise(1L);
        Mockito.doNothing().when(franchiseRepository).save(Mockito.any(Franchise.class));

        // when
        franchiseService.saveFranchise(request);

        // then
        then(franchiseRepository)
                .should(times(1))
                .save(any());
    }


    @Test
    @DisplayName("프랜차이즈 - 중복으로 인한 실패")
    void saveFranchiseFailedWhenDuplicateFranchiseName() {
        CreateFranchiseRequest request = franchiseRequest();
        Franchise testFranchise = toDomain(request);

        given(franchiseRepository.findByName(request.name())).willReturn(Optional.of(testFranchise));

        assertThatThrownBy(() -> franchiseService.saveFranchise(request))
                .isInstanceOf(FranchiseAlreadyExistException.class);

        then(franchiseRepository)
                .should(times(1))
                .findByName(request.name());
    }


    private Franchise toDomain(CreateFranchiseRequest request) {
        return Franchise.builder()
                .name(request.name())
                .imageUrl(request.imageUrl())
                .build();
    }

    private CreateFranchiseRequest franchiseRequest() {
        return CreateFranchiseRequest.builder()
                .name("test")
                .imageUrl("test")
                .build();
    }
    private Franchise franchise(long id) {
        return Franchise.builder()
                .id(id)
                .name("test2")
                .imageUrl("test2")
                .build();
    }

}