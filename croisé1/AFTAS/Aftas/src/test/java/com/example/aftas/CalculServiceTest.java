package com.example.aftas;

import com.example.aftas.dto.HuntingResp;
import com.example.aftas.dto.RankingResp;
import com.example.aftas.entity.Competition;
import com.example.aftas.entity.Ranking;
import com.example.aftas.exception.ResourceNotFoundException;
import com.example.aftas.repository.CompetitionRepository;
import com.example.aftas.repository.RankingRepository;
import com.example.aftas.services.implementation.HuntingService;
import com.example.aftas.services.implementation.RankingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class CalculServiceTest {

    @Mock
    private CompetitionRepository competitionRepository;

    @Mock
    private HuntingService huntingService;

    @Mock
    private RankingRepository rankingRepository;

    @InjectMocks
    private RankingService rankingService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCalculateAndSetRankings() {
        String competitionCode = "yourCompetitionCode";
        Competition competition = new Competition();
        List<RankingResp> expectedRankings = new ArrayList<>();
        when(competitionRepository.findById(competitionCode)).thenReturn(Optional.of(competition));
        List<HuntingResp> huntingResults = new ArrayList<>();
        when(huntingService.getHuntByCompetition(competitionCode)).thenReturn(huntingResults);
        List<RankingResp> rankings = rankingService.calculateAndSetRankings(competitionCode);
        int expectedNumberOfTimes = rankings.size();
        verify(rankingRepository, times(expectedNumberOfTimes)).save(any(Ranking.class));
        assertEquals(expectedRankings, rankings);
    }

    @Test
    public void testInvalidCompetitionCode() {
        String competitionCode = "invalidCompetitionCode";
        when(competitionRepository.findById(competitionCode)).thenReturn(Optional.empty());

        verify(huntingService, never()).getHuntByCompetition(anyString());
        verify(rankingRepository, never()).save(any(Ranking.class));
        assertThrows(ResourceNotFoundException.class, () -> rankingService.calculateAndSetRankings(competitionCode));

    }

    @Test
    public void testEmptyHuntingResults() {
        String competitionCode = "emptyHuntingResults";
        Competition competition = new Competition();
        when(competitionRepository.findById(competitionCode)).thenReturn(Optional.of(competition));
        when(huntingService.getHuntByCompetition(competitionCode)).thenReturn(new ArrayList<>());

        List<RankingResp> rankings = rankingService.calculateAndSetRankings(competitionCode);

        verify(rankingRepository, never()).save(any(Ranking.class));
        assertEquals(0, rankings.size());
    }


}
