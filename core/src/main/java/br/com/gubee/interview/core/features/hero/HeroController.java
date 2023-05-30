package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/heroes", produces = APPLICATION_JSON_VALUE)
public class HeroController {

    private final HeroService heroService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Validated
                                       @RequestBody CreateHeroRequest createHeroRequest) {
        final UUID id = heroService.create(createHeroRequest);
        return created(URI.create(format("/api/v1/heroes/%s", id))).build();
    }

    @PatchMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@PathVariable UUID id, @Validated @RequestBody CreateHeroRequest createHeroRequest){
        Object hero = heroService.findById(id);
        if(Objects.isNull(hero)){
            return notFound().build();
        }else{
            heroService.update(createHeroRequest, id);
            return ok().build();
        }
    }

    @DeleteMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> delete(@Validated @PathVariable UUID id){
        Object hero = heroService.findById(id);
        if(Objects.isNull(hero)){
            return notFound().build();
        } else{
            heroService.delete(id);
            return ok().body(hero);
        }
    }

    @GetMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findById(@PathVariable UUID id){
        Object hero = heroService.findById(id);
        if(Objects.isNull(hero)){
            System.out.println("Hero not found");
            return notFound().build();
        } else {
            return ok().body(hero);
        }
    }

    @GetMapping(value = "/nome/{name}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByName(@PathVariable String name){
        Object hero = heroService.findByName(name);
        if(Objects.isNull(hero)){
            return ok().build();
        } else{
            return ok().body(hero);
        }
    }




}

















