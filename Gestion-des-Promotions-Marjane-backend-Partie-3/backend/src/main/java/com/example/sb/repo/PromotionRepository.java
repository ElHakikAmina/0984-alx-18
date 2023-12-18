package com.example.sb.repo;

import com.example.sb.model.Entities.Promotions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotions,Long> {
        public Optional<Promotions> findById(Long id);

}
