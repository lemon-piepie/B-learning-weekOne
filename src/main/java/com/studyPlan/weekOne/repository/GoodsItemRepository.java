package com.studyPlan.weekOne.repository;

import com.studyPlan.weekOne.entity.GoodsItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GoodsItemRepository extends JpaRepository<GoodsItem, Integer> {
    Optional<List<GoodsItem>> findAllByName(String name);
}
