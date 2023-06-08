package com.depromeet.franchise.service;


import com.depromeet.common.exception.franchise.FranchiseAlreadyExistException;
import com.depromeet.common.exception.franchise.FranchiseImageUrlUpdateNotAllowedException;
import com.depromeet.domain.franchise.infrastructure.persistence.FranchiseRepository;
import com.depromeet.domain.franchise.domain.Franchise;
import com.depromeet.franchise.dto.request.CreateFranchiseRequest;
import com.depromeet.franchise.dto.request.ModifyFranchiseImageRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
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
        var request = createFranchiseRequest();
        var franchise = franchise(1L);
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
        var request = createFranchiseRequest();
        var testFranchise = toDomain(request);

        given(franchiseRepository.findByName(request.name())).willReturn(Optional.of(testFranchise));

        assertThatThrownBy(() -> franchiseService.saveFranchise(request))
                .isInstanceOf(FranchiseAlreadyExistException.class);

        then(franchiseRepository)
                .should(times(1))
                .findByName(request.name());
    }

    @Test
    @DisplayName("프랜차이즈 이미지 Url 수정 - 성공")
    void modifyFranchiseImageUrl() {
        // given
        var request = modifyFranchiseImageUrlRequest();
        var franchise = franchise(1L);

        given(franchiseRepository.findById(1L)).willReturn((franchise));

        // when
        franchiseService.modifyFranchiseImage(1L, request);

        // then
        assertThat(franchise.getImageUrl()).isEqualTo(request.imageUrl());

        then(franchiseRepository)
                .should(times(1))
                .modifyImageUrl(any());
    }

    @Test
    @DisplayName("프랜차이즈 이미지 Url 수정 - 실패 (이미 같은 이미지 url)")
    void modifyFranchiseImageUrlWhenUrlIsForbidden() {
        // given
        var request = modifyFranchiseImageUrlBadRequest();
        var franchise = franchise(1L);

        given(franchiseRepository.findById(1L)).willReturn((franchise));

        // when
        assertThatThrownBy(() -> franchiseService.modifyFranchiseImage(1L, request))
                .isInstanceOf(FranchiseImageUrlUpdateNotAllowedException.class);

        // then
        assertThat(franchise.getImageUrl()).isEqualTo(request.imageUrl());

        then(franchiseRepository)
                .should(never())
                .modifyImageUrl(any());
    }

    @Test
    @DisplayName("프랜차이즈 전체 목록 조회 - 성공")
    void finaAllFranchise() {
        // given
        var firstFranchise = franchise(1L);
        var secondeFranchise = franchise(2L);
        var thirdFranchise = franchise(3L);
        var franchises = Arrays.asList(firstFranchise, secondeFranchise, thirdFranchise);


        Mockito.when(franchiseRepository.findAll()).thenReturn(franchises);

        // when
        var findFranchises = franchiseService.findAllFranchise();

        // then
        assertThat(findFranchises.size() == 3);
        assertThat(findFranchises.isEmpty()).isFalse();
    }

    private ModifyFranchiseImageRequest modifyFranchiseImageUrlRequest() {
        return ModifyFranchiseImageRequest.builder()
                .imageUrl("https://oversweet.s3/test")
                .build();
    }

    private ModifyFranchiseImageRequest modifyFranchiseImageUrlBadRequest() {
        return ModifyFranchiseImageRequest.builder()
                .imageUrl("test2")
                .build();
    }

    private Franchise toDomain(CreateFranchiseRequest request) {
        return Franchise.builder()
                .name(request.name())
                .imageUrl(request.imageUrl())
                .build();
    }

    private CreateFranchiseRequest createFranchiseRequest() {
        return CreateFranchiseRequest.builder()
                .name("test")
                .imageUrl("test")
                .build();
    }

    private Franchise franchise(Long id) {
        return Franchise.builder()
                .id(id)
                .name("test2")
                .imageUrl("test2")
                .build();
    }

}