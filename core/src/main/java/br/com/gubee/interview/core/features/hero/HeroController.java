package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.features.powerstats.PowerStatsService;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/heroes", produces = APPLICATION_JSON_VALUE)
public class HeroController {

    private final HeroService heroService;
    private final PowerStatsService powerStatsService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Validated
                                       @RequestBody CreateHeroRequest createHeroRequest) {
        final UUID id = heroService.create(createHeroRequest);
        return created(URI.create(format("/api/v1/heroes/%s", id))).build();
    }

    @PatchMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@PathVariable UUID id, @Validated @RequestBody CreateHeroRequest createHeroRequest){
        List<Hero> hero = heroService.findById(id);
        if(hero.isEmpty()){
            System.out.println("Hero not found");
            return notFound().build();
        }else{
            System.out.println("Hero updated");
            heroService.update(createHeroRequest, id);
            return ok().build();
        }
    }

    @DeleteMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> delete(@Validated @PathVariable UUID id){
        List<Hero> hero = heroService.findById(id);
        if(hero.isEmpty()){
            return notFound().build();
        } else{
            System.out.println("Hero successfully deleted");
            heroService.delete(id);
            return ok().build();
        }
    }

    @GetMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Hero>> findById(@PathVariable UUID id){
        List<Hero> hero = heroService.findById(id);

        if(hero.isEmpty()){
            System.out.println("Hero not found");
            return notFound().build();
        } else {
            System.out.println("Hero was found");
            return ok().body(hero);
        }
    }

    @GetMapping(value = "/nome/{name}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Hero>> findByName(@PathVariable String name){
        List<Hero> hero = heroService.findByName(name);
        if(hero.isEmpty()){
            System.out.println("Hero not found");
            return ok().build();
        } else{
            return ok().body(hero);
        }
    }


    @GetMapping(value = "/teste/{id}/{ids}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> test(@PathVariable(name = "id") UUID id, @PathVariable(name = "ids") UUID ids){
        Hero hero1 = heroService.test(id);
        Hero hero2 = heroService.test(ids);
        PowerStats ps1 = powerStatsService.compare(hero1.getPowerStatsId());
        PowerStats ps2 = powerStatsService.compare(hero2.getPowerStatsId());

        int forca = ps1.getStrength() - ps2.getStrength();
        int agilidade = ps1.getAgility() - ps2.getAgility();
        int destreza = ps1.getDexterity() - ps2.getDexterity();
        int inteligencia = ps1.getIntelligence() - ps2.getIntelligence();

        if(Objects.isNull(hero1) || Objects.isNull(hero2)){
            System.out.println("Heroes not found");
            return notFound().build();
        } else {
            return ok().body(hero1.getName() + ": " + hero1.getId() + "\n" +
                    hero2.getName() + ": " + hero2.getId() + "\n" +
                    "Diferença de força: " + forca + "\n" +
                    "Diferença de agilidade: " + agilidade + "\n" +
                    "Diferença de destreza: " + destreza + "\n" +
                    "Diferença de inteligencia: " + inteligencia);
        }
    }


}























