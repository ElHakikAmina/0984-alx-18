package com.example.aftas.repository;

import com.example.aftas.entity.Fish;
import com.example.aftas.entity.Level;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FishRepository extends JpaRepository<Fish, String> {
    List<Fish> findByLevel(Level level);
    @Query(value = "SELECT * FROM fish ORDER BY RANDOM() LIMIT 3", nativeQuery = true)
    List<Fish> getRandomFishes();

}
