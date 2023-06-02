package br.com.gubee.interview.model.request;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.enums.Race;
import lombok.*;

import javax.validation.constraints.NotNull;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class CompareRequest {

    private String name;
    private int strength;
    private int agility;
    private int dexterity;
    private int intelligence;

    public CompareRequest(Hero entity, PowerStats entityPS){
        this.name = entity.getName();
        this.strength = entityPS.getStrength();
        this.agility = entityPS.getAgility();
        this.dexterity = entityPS.getDexterity();
        this.intelligence = entityPS.getIntelligence();
    }

}
