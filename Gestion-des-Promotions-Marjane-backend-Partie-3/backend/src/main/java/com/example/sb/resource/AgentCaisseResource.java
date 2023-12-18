package com.example.sb.resource;

import com.example.sb.model.Entities.AgentCaisse;
import com.example.sb.model.dto.AgentCaisseDto;
import com.example.sb.model.dto.AgentCaisseDtoRequest;

import com.example.sb.service.Impl.AgentCaisseManagerApplicationImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/agentCaisse")
@CrossOrigin(origins = "*")

public class AgentCaisseResource extends  Resource<AgentCaisseDto, AgentCaisseDtoRequest,Long>{
    @Autowired
    public void setService(
          AgentCaisseManagerApplicationImpl service) {
        this.service = service;
    }

}