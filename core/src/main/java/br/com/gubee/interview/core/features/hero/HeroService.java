package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.features.powerstats.PowerStatsService;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final HeroRepository heroRepository;
    private final PowerStatsService powerStatsService;

    @Transactional
    public UUID create(CreateHeroRequest createHeroRequest) {
        PowerStats powerStats = PowerStats.builder()
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .agility(createHeroRequest.getAgility())
                .dexterity(createHeroRequest.getDexterity())
                .intelligence(createHeroRequest.getIntelligence())
                .strength(createHeroRequest.getStrength())
                .build();
        UUID id =  powerStatsService.create(powerStats);
        return heroRepository.create(new Hero(createHeroRequest, id));
    }

    @Transactional
    public int update(CreateHeroRequest createHeroRequest, UUID id) {
        PowerStats powerStats = PowerStats.builder()
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .agility(createHeroRequest.getAgility())
                .dexterity(createHeroRequest.getDexterity())
                .intelligence(createHeroRequest.getIntelligence())
                .strength(createHeroRequest.getStrength())
                .build();
        UUID psId = powerStatsService.create(powerStats);
        return heroRepository.update(new Hero(createHeroRequest, psId), id);
    }

    @Transactional
    public int delete(UUID id) {
        return heroRepository.delete(id);
    }

    public List<Hero> findById(UUID id){
        return heroRepository.findById(id);
    }

    public List<Hero> findByName(String name) {
        return heroRepository.findByName(name);
    }


}

