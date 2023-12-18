
package com.example.sb.service.Impl;
        import com.example.sb.model.Entities.Promotions;
        import com.example.sb.model.dto.PromotionRequest;
        import com.example.sb.model.dto.PromotionsDto;
        import com.example.sb.model.mappers.Mapper;
        import com.example.sb.repo.PromotionRepository;
        import com.example.sb.service.PaginationService;
        import com.example.sb.service.PromotionManagerApplication;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Qualifier;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.PageRequest;
        import org.springframework.stereotype.Service;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Optional;
        import java.util.stream.Collectors;

@Service
public class PromotionManagerApplicationImpl implements PromotionManagerApplication, PaginationService<PromotionsDto,PromotionRequest,Long> {
    private final PromotionRepository repository;
    private final Mapper<Promotions, PromotionsDto> promotionmapper;
    private final PromotionObservable promotionObservable;
    @Autowired
    public PromotionManagerApplicationImpl(
            PromotionRepository repository,
            @Qualifier("promotionMapper")
            Mapper<Promotions, PromotionsDto> promotionmapper,
            PromotionObservable promotionObservable) {
        this.repository = repository;
        this.promotionmapper = promotionmapper;
        this.promotionObservable=promotionObservable;
    }
    @Override
    public PromotionsDto save(PromotionRequest promotionRequest) {
        var PromotionEntity =promotionRequest.toModel();
        var createdPromotion = promotionmapper.mapTo(repository.save(PromotionEntity));
        promotionObservable.notifyObservers(createdPromotion);
        return createdPromotion;
    }

    public Promotions save(Promotions promotionRequest) {
        System.out.println("teest");
        return repository.save(promotionRequest);
    }

    public Optional<Promotions> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<PromotionsDto> getAll() {
        return repository.findAll()
                .stream()
                .map(promotionmapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public PromotionsDto update(final Long id, final PromotionsDto promotionsDto) {
        Optional<Promotions> optionalPromotion = repository.findById(id);

        if (optionalPromotion.isPresent()) {
            Promotions existingPromotion = optionalPromotion.get();

            // Update the fields based on the information in promotionsDto
            existingPromotion.setDatepromo(promotionsDto.getDatepromo());
            existingPromotion.setStatut(promotionsDto.getStatut());
            existingPromotion.setQuantity(promotionsDto.getQuantity());

            // Save the updated promotion
            Promotions updatedPromotion = repository.save(existingPromotion);

            // Map the updated promotion to PromotionsDto and return it
            return promotionmapper.mapTo(updatedPromotion);
        } else {
            // Handle the case where the promotion with the given ID is not found
            throw new RuntimeException("Promotion not found with id: " + id);
        }
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public PromotionsDto find(final Long id) {
        var optionalEntity = repository.findById(id);
        return optionalEntity.isPresent() ?
                promotionmapper.mapTo(optionalEntity.get())
                :
                null;
    }


    @Override
    public PromotionsDto partialUpdate(final Long id, final PromotionsDto promotionsDto) {
        promotionsDto.setId(id);
        var  responsable = promotionmapper.mapFrom(promotionsDto);

        return repository.findById(id).map(founded -> {
            Optional.ofNullable(responsable.getResponsable()).ifPresent(founded::setResponsable);
            Optional.ofNullable(responsable.getDatepromo()).ifPresent(founded::setDatepromo);
            Optional.ofNullable(responsable.getCategorie()).ifPresent(founded::setCategorie);
            Optional.ofNullable(responsable.getStatut()).ifPresent(founded::setStatut);
            Optional.ofNullable(responsable.getProduit()).ifPresent(founded::setProduit);
            Optional.ofNullable(responsable.getQuantity()).ifPresent(founded::setQuantity);
            return promotionmapper.mapTo(repository.save(founded));
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
    public Page<PromotionsDto> getAllPages(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<Promotions> promotions = repository.findAll(pageRequest);
        return promotions
                .map(this.promotionmapper::mapTo);

    }
}
