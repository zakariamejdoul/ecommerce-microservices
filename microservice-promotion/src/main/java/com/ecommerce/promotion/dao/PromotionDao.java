package com.ecommerce.promotion.dao;

import com.ecommerce.promotion.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionDao extends JpaRepository<Promotion, Long> {
}
