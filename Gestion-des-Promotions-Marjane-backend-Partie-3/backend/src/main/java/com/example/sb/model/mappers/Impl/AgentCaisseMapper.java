package com.example.sb.model.mappers.Impl;

import com.example.sb.model.Entities.AgentCaisse;
import com.example.sb.model.dto.AgentCaisseDto;
import com.example.sb.model.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AgentCaisseMapper  implements Mapper<AgentCaisse, AgentCaisseDto> {

    private final ModelMapper modelMapper;


    public AgentCaisseMapper( ModelMapper modelMapper) {

        this.modelMapper = modelMapper;
    }

    @Override
    public AgentCaisseDto mapTo(AgentCaisse agentCaisse) {

        return modelMapper.map(agentCaisse, AgentCaisseDto.class);
    }

    @Override
    public AgentCaisse mapFrom(AgentCaisseDto agentCaisseDto) {
        return modelMapper.map(agentCaisseDto, AgentCaisse.class);
    }


}
