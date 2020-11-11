package com.studyPlan.weekOne.repository;

import com.studyPlan.weekOne.entity.GoodsItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsItemRepository extends JpaRepository<GoodsItem, Integer> {
}
