package com.depromeet.domain.drink;

import com.depromeet.domain.common.AuditingTimeEntity;
import com.depromeet.domain.franchise.Franchise;
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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "drink")
public class Drink extends AuditingTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id", foreignKey = @ForeignKey(name = "FK_DRINK_FRANCHISE"), nullable = false)
    private Franchise franchise;

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
}
