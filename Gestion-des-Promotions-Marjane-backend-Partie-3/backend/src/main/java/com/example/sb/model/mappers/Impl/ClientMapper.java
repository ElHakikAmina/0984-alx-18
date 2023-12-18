package com.example.sb.model.mappers.Impl;

import com.example.sb.model.Entities.Client;
import com.example.sb.model.dto.ClientDto;
import com.example.sb.model.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class ClientMapper  implements Mapper<Client, ClientDto> {

    private final ModelMapper modelMapper;


    public ClientMapper(final ModelMapper modelMapper) {

        this.modelMapper = modelMapper;
    }

    @Override
    public ClientDto mapTo(Client client) {

        return modelMapper.map(client, ClientDto.class);
    }

    @Override
    public Client mapFrom(ClientDto clientDto) {
        return modelMapper.map(clientDto, Client.class);
    }


}
