package com.example.sb.repo;

import com.example.sb.model.Entities.AgentCaisse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentCaisseRepository  extends JpaRepository<AgentCaisse,Long> {
    public boolean existsByEmail(String email);

}
