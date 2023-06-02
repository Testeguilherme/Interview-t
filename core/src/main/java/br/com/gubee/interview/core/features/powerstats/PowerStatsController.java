package br.com.gubee.interview.core.features.powerstats;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/power", produces = APPLICATION_JSON_VALUE)
public class PowerStatsController {

    private final PowerStatsService powerStatsService;

//    @GetMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<Map<String, Object>>> compare(@PathVariable UUID id){
//        List<Map<String, Object>> ps = powerStatsService.compare(id);
//        if(ps.isEmpty()){
//            System.out.println("Power Stats not found");
//            return notFound().build();
//        } else {
//            System.out.println("Power Stats was found");
//            return ok().body(ps);
//        }
//    }

}
