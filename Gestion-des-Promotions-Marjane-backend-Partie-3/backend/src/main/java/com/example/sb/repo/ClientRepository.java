package com.example.sb.repo;

import com.example.sb.model.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository  extends JpaRepository<Client,Long> {
    public boolean existsByEmail(String email);

        boolean existsBynumerocartefideliter(Integer numerocartefideliter);
}