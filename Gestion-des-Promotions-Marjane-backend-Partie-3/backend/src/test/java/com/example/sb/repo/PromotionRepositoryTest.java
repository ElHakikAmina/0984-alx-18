package com.example.sb.repo;

import com.example.sb.model.Ennum.Statut;
import com.example.sb.model.Entities.*;
import com.example.sb.model.dto.PromotionsDto;
import com.example.sb.service.Impl.PromotionManagerApplicationImpl;
import com.example.sb.service.PromotionManagerApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat; // Add the import for assertThat
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)

class PromotionRepositoryTest {
    @Mock
    private PromotionRepository promotionRepository;
    @InjectMocks
    private PromotionManagerApplicationImpl service;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Test
    void shouldCheckIfPromotionExistsById() throws ParseException {
        Centre centre = new Centre(1L, "safi");
        Admin admin = new Admin(1L, "Sara@gmail", "Sara", centre);
        Rayon rayon = new Rayon(1L, "rayon");
        Responsable responsable = new Responsable(1L, "fadwa", "123", admin, rayon);
        Categories categories = new Categories(1L, "catrgorie", rayon);
        Produits produits = new Produits(1L, "produit", categories);

        Statut statut = Statut.IN_PROCESS;
        String dateString = "2023-11-17";
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        Promotions promotions = new Promotions(1L, responsable, categories, produits, localDate, statut, 1);

        when(promotionRepository.save(promotions)).thenReturn(promotions);

        Promotions pr = service.save(promotions);

        assertEquals(promotions, pr);
        verify(promotionRepository, times(1)).save(promotions);
    }

    @Test
    void testGetAllPromotions() {
        // Given
        List<Promotions> promotions = Arrays.asList(new Promotions(), new Promotions());
        when(promotionRepository.findAll()).thenReturn(promotions);

        // When
        List<PromotionsDto> result = service.getAll();

        // Then
        assertEquals(promotions.size(), result.size());
        verify(promotionRepository, times(1)).findAll();
    }
}
