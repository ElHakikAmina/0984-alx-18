package com.example.sb.service.Impl;

import com.example.sb.model.Entities.Client;
import com.example.sb.model.dto.ClientDto;
import com.example.sb.model.dto.ClientDtoRequest;
import com.example.sb.model.mappers.Mapper;
import com.example.sb.repo.ClientRepository;
import com.example.sb.service.ClientManagerApplication;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientManagerApplicationImpl implements ClientManagerApplication {
    private final ClientRepository repository;
    private final EmailSender emailSender;
    private final Mapper<Client, ClientDto> mapper;
    public ClientManagerApplicationImpl(
            EmailSender emailSender,
            ClientRepository repository,
            @Qualifier("clientMapper") Mapper<Client, ClientDto> mapper
    ){
        this.emailSender=emailSender;
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override
    public ClientDto save(ClientDtoRequest clientDtoRequest) {
        String emailToCheckemail = clientDtoRequest.getEmail();
        Integer emailToCheckcartefidelite = clientDtoRequest.getNumerocartefideliter();
        if (repository.existsByEmail(emailToCheckemail)) {
            throw new RuntimeException("Email already exists in the database");
        }
        if (repository.existsBynumerocartefideliter(emailToCheckcartefidelite)) {
            throw new RuntimeException("Numero du carte fideliter already exists in the database");
        }

        var Entity =clientDtoRequest.toModel();
        System.out.println(Entity);
        var created = mapper.mapTo(repository.save(Entity));
        String emailBody = "Thank you for registering!\n" +
                "Your password: " + clientDtoRequest.getPassword() + "\n" +
                "Your loyalty card number: " + clientDtoRequest.getNumerocartefideliter();

        emailSender.sendMail(emailToCheckemail, new String[]{}, "Registration Successful", emailBody);
        return created;

    }

    @Override
    public List<ClientDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDto update(Long aLong, ClientDto clientDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ClientDto find(Long id) {
        return repository.findById(id)
                .map(mapper::mapTo)
                .orElse(null);    }

    @Override
    public ClientDto partialUpdate(Long aLong, ClientDto clientDto) {
        return null;
    }

    @Override
    public boolean isExist(Long id) {
        return repository.existsById(id);      }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Page<ClientDto> getAllPages(int page, int size) {
        return null;
    }

}
