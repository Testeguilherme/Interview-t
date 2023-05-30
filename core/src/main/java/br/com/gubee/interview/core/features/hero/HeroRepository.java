package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.Hero;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class HeroRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    private static final String CREATE_HERO_QUERY = "INSERT INTO hero" +
            " (name, race, power_stats_id)" +
            " VALUES (:name, :race, :powerStatsId) RETURNING id";
    UUID create(Hero hero) {
        final Map<String, Object> params = Map.of(
                "name", hero.getName(),
            "race", hero.getRace().name(),
            "powerStatsId", hero.getPowerStatsId());

        return namedParameterJdbcTemplate.queryForObject(
            CREATE_HERO_QUERY,
            params,
            UUID.class);
    }


    private static final String UPDATE_HERO_QUERY = "UPDATE hero SET name=:name, race=:race, " +
            "power_stats_id=:powerStatsId WHERE " +
            "id= :id";
    public int update(Hero hero, UUID id) {
        final Map<String, Object> params = Map.of(
                "name", hero.getName(),
                "race", hero.getRace().name(),
                "powerStatsId", hero.getPowerStatsId(),
                "id", id);
        return namedParameterJdbcTemplate.update(
                UPDATE_HERO_QUERY,
                params);
    }


    private static final String DELETE_HERO_QUERY = "DELETE FROM hero WHERE id=:id";

    public int delete(UUID id) {
        return namedParameterJdbcTemplate.update(
                DELETE_HERO_QUERY,
                Map.of("id", id));
    }



    private static final String FINDBYID_HERO_QUERY = "SELECT * FROM hero WHERE id=:t";

    public List<Hero> findById(UUID id) {
            return namedParameterJdbcTemplate.query(
                    FINDBYID_HERO_QUERY,
                    Map.of("t", id),
                    new BeanPropertyRowMapper(Hero.class));
    }



    private static final String FINDBYNAME_HERO_QUERY = "SELECT * FROM hero WHERE name=:t";

    public List<Hero> findByName(String name) {
        return namedParameterJdbcTemplate.query(
                FINDBYNAME_HERO_QUERY,
                Map.of("t", name),
                new BeanPropertyRowMapper(Hero.class));
    }



}
