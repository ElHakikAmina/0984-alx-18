package com.example.aftas.repository;

import com.example.aftas.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LevelRepository extends JpaRepository<Level , Long> {
    List<Level> findAllByIdLessThan(Long id);
    List<Level> findAllByIdGreaterThan(Long id);

}
