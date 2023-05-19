package com.depromeet.domain.franchise;


import com.depromeet.domain.common.AuditingTimeEntity;
import com.depromeet.domain.drink.Drink;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "franchises")
public class Franchise extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "franchise_id")
    private Long franchiseId;

    private String franchiseName;

    @OneToMany(mappedBy = "franchise", orphanRemoval = true)
    private List<Drink> drinks = new ArrayList<>();
}
