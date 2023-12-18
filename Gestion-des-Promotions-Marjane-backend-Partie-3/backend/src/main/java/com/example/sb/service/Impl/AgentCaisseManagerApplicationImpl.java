package com.example.sb.service.Impl;

import com.example.sb.model.Entities.AgentCaisse;
import com.example.sb.model.dto.AgentCaisseDto;
import com.example.sb.model.dto.AgentCaisseDtoRequest;
import com.example.sb.model.mappers.Mapper;
import com.example.sb.repo.AgentCaisseRepository;
import com.example.sb.service.AgentCaisseManagerApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AgentCaisseManagerApplicationImpl implements AgentCaisseManagerApplication {
        private final AgentCaisseRepository repository;
        private final Mapper<AgentCaisse,AgentCaisseDto> mapper;
        @Autowired

        public AgentCaisseManagerApplicationImpl(
          AgentCaisseRepository repository,
          @Qualifier("agentCaisseMapper") Mapper<AgentCaisse,AgentCaisseDto> mapper
        ){
            this.repository = repository;
            this.mapper = mapper;
        }
    @Override
    public AgentCaisseDto save(AgentCaisseDtoRequest agentCaisseDtoRequest) {
        String emailToCheck = agentCaisseDtoRequest.getEmail();

        if (repository.existsByEmail(emailToCheck)) {
            throw new RuntimeException("Email already exists in the database");
        }
        var Entity =agentCaisseDtoRequest.toModel();
        var created = mapper.mapTo(repository.save(Entity));
        return created;
    }

    @Override
    public List<AgentCaisseDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public AgentCaisseDto update(Long aLong, AgentCaisseDto agentCaisseDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public AgentCaisseDto find(Long id) {
        return repository.findById(id)
                .map(mapper::mapTo)
                .orElse(null);
    }

    @Override
    public AgentCaisseDto partialUpdate(Long aLong, AgentCaisseDto agentCaisseDto) {
        return null;
    }

    @Override
    public boolean isExist(Long id) {
        return repository.existsById(id);    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Page<AgentCaisseDto> getAllPages(int page, int size) {
        return null;
    }
}