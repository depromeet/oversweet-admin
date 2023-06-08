package com.depromeet.domain.drink.entity;

import com.depromeet.domain.common.AuditingTimeEntity;
import com.depromeet.domain.drink.domain.Drink;
import com.depromeet.domain.drink.domain.DrinkCategory;
import com.depromeet.domain.franchise.entity.FranchiseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "drink")
public class DrinkEntity extends AuditingTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id", foreignKey = @ForeignKey(name = "FK_DRINK_FRANCHISE"), nullable = false)
    private FranchiseEntity franchise;

    @Column(name = "size", nullable = false)
    private int size;

    @Column(name = "sugar")
    private int sugar;

    @Column(name = "calorie")
    private int calorie;

    @Column(name = "image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private DrinkCategory category;

    @Builder
    public DrinkEntity(final Long id, final String name, final FranchiseEntity franchise, final int size, final int sugar, final int calorie, final String imageUrl, final DrinkCategory category) {
        this.id = id;
        this.name = name;
        this.franchise = franchise;
        this.size = size;
        this.sugar = sugar;
        this.calorie = calorie;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public void modifyImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Drink toDomain(){
        return Drink.builder()
                .id(this.id)
                .name(this.name)
                .franchise(this.franchise.toDomain())
                .size(this.size)
                .sugar(this.sugar)
                .calorie(this.calorie)
                .imageUrl(this.imageUrl)
                .category(this.category)
                .build();

    }
}
