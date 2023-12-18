package com.example.sb.service.Impl;

import com.example.sb.model.dto.PromotionsDto;
import com.example.sb.service.PromotionObserver;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("singleton")
public class PromotionObservable {
    private List<PromotionObserver> observers = new ArrayList<>();

    public void addObserver(PromotionObserver observer) {

        observers.add(observer);
    }

    public void notifyObservers(PromotionsDto promotion) {
        for (PromotionObserver observer : observers) {
            observer.update(promotion);
        }
    }
}
