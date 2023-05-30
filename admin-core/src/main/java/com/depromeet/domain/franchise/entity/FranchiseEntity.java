package com.depromeet.domain.franchise.entity;


import com.depromeet.domain.common.AuditingTimeEntity;
import com.depromeet.domain.franchise.domain.Franchise;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "franchise")
public class FranchiseEntity extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @Builder
    public FranchiseEntity(final Long id, final String name, final String imageUrl){
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public void modifyImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Franchise toDomain(){
        return Franchise.builder()
                .id(this.id)
                .name(this.name)
                .imageUrl(this.imageUrl)
                .build();
    }
}
