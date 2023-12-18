package com.api.AFTAS.domains.level;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LevelRepository extends JpaRepository<Level,Integer> {
    List<Level> findAllByPointsGreaterThanEqual(Integer Points);
    List<Level> findAllByPointsGreaterThan(Integer Points);
}
