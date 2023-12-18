package com.WI.WIGOLDFISH.repositories;

import com.WI.WIGOLDFISH.entities.fish.Fish;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FishRepository extends JpaRepository<Fish, String> {
    Optional<Fish> findByName(String code);
}
