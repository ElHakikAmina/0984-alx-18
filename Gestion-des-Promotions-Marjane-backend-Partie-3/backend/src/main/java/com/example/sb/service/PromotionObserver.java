package com.example.sb.service;

import com.example.sb.model.dto.PromotionsDto;

public interface PromotionObserver {
    void update(PromotionsDto promotion);
}

