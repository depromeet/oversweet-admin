package com.depromeet.franchise.service;


import com.depromeet.domain.franchise.Franchise;
import com.depromeet.domain.franchise.FranchiseRepository;
import com.depromeet.franchise.dto.request.CreateFranchiseRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.misusing.FriendlyReminderException;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.will;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

@DisplayName("프랜차이즈 서비스 테스트")
@MockitoSettings
class FranchiseServiceTest {

    @Mock
    private FranchiseRepository franchiseRepository;

    @InjectMocks
    FranchiseService franchiseService;

    @Test
    @DisplayName("프랜차이즈 - 성공")
    void saveFranchise() {
        // given
        CreateFranchiseRequest createFranchiseRequest = franchiseRequest();
        Franchise franchise = franchise(1L);

        given(franchiseRepository.save(any())).willReturn(franchise);

        // when
        franchiseService.saverFranchise(createFranchiseRequest);

        // then
        then(franchiseRepository)
                .should(times(1))
                .save(any());
    }

    @Test
    @DisplayName("프랜차이즈 - 중복으로인한 실패")
    void saveFranchiseFailedWhenDuplicate() {
        // given
        CreateFranchiseRequest createFranchiseRequest = franchiseRequest();

        given(franchiseRepository.findByName(createFranchiseRequest.getName())).willThrow(FriendlyReminderException.class);

        // when
        assertThatThrownBy(() -> franchiseService.saverFranchise(createFranchiseRequest))
                .isInstanceOf(FriendlyReminderException.class);

        then(franchiseRepository)
                .should(times(1))
                .findByName(createFranchiseRequest.getName());
        then(franchiseRepository)
                .should(never())
                .findById(anyLong());
    }

    private CreateFranchiseRequest franchiseRequest(){
        return CreateFranchiseRequest.builder()
                .name("test")
                .imageUrl("test")
                .build();
    }
    private Franchise franchise(long id){
        return Franchise.builder()
                .id(id)
                .name("test")
                .imageUrl("test")
                .build();
    }
}