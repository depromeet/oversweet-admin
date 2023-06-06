package com.depromeet.domain.drink;

import com.depromeet.domain.drink.domain.Drink;
import com.depromeet.domain.franchise.domain.Franchise;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface DrinkRepository {
    void save(Drink drink);
    Page<Drink> findAllByFranchise(Franchise franchise, List<Integer> range);
    Optional<Drink> findByFranchise(Franchise franchise, String name);
}
