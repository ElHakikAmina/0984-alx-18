package com.example.sb.service.Impl;
import com.example.sb.model.Entities.Responsable;
import com.example.sb.model.dto.PromotionsDto;
import com.example.sb.model.dto.ResponsableDto;
import com.example.sb.model.dto.ResponsableRequest;
import com.example.sb.model.mappers.Mapper;
import com.example.sb.repo.ResponsableRepository;
import com.example.sb.service.PromotionObserver;
import com.example.sb.service.ResponsableManagerApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResponsableManagerApplicationImpl implements ResponsableManagerApplication , PromotionObserver {
    private final ResponsableRepository repository;
    private final Mapper<Responsable, ResponsableDto> responsablemapper;
    private  final  PromotionObservable promotionObservable;
    @Autowired
    public ResponsableManagerApplicationImpl(
            ResponsableRepository repository,
            @Qualifier("responsableMapper") Mapper<Responsable, ResponsableDto> responsablemapper,
            PromotionObservable promotionObservable
         ) {
        this.repository = repository;
        this.responsablemapper = responsablemapper;
        this.promotionObservable=promotionObservable;
        this.promotionObservable.addObserver(this);
    }
    @Override
  public ResponsableDto save(ResponsableRequest responsableRequest) {
        var responsableEntity =responsableRequest.toModel();
        var createdResponsable = responsablemapper.mapTo(repository.save(responsableEntity));
        return createdResponsable;
    }


    @Override
    public List<ResponsableDto> getAll() {
        return repository.findAll()
                         .stream()
                         .map(responsablemapper::mapTo)
                         .collect(Collectors.toList());
    }

    @Override
    public ResponsableDto update(final Long id, final ResponsableDto responsableDto) {
        responsableDto.setId(id);
        //return save(responsableDto);
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ResponsableDto find(final Long id) {
        var optionalEntity = repository.findById(id);
        return optionalEntity.isPresent() ?
                responsablemapper.mapTo(optionalEntity.get())
                :
                null;
    }


    @Override
    public ResponsableDto partialUpdate(final Long id, final ResponsableDto responsableDto) {
        responsableDto.setId(id);
        var  responsable = responsablemapper.mapFrom(responsableDto);

        return repository.findById(id).map(founded -> {
            Optional.ofNullable(responsable.getEmail()).ifPresent(founded::setEmail);
            Optional.ofNullable(responsable.getRayon()).ifPresent(founded::setRayon);

            return responsablemapper.mapTo(repository.save(founded));
        }).orElseThrow(() -> new RuntimeException("level not found"));
    }

    @Override
    public boolean isExist(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Page<ResponsableDto> getAllPages(int page, int size) {
        return null;
    }


    @Override
    public void update(PromotionsDto promotion) {
        System.out.println("Notification au responsable : Une nouvelle promotion a été ajoutée - " + promotion.toString());
    }
}
