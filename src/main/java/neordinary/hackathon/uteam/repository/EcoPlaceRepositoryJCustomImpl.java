package neordinary.hackathon.uteam.repository;

import neordinary.hackathon.uteam.constant.PlaceCategory;
import neordinary.hackathon.uteam.domain.Address;
import neordinary.hackathon.uteam.domain.EcoPlace;
import neordinary.hackathon.uteam.domain.Position;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;

public class EcoPlaceRepositoryJCustomImpl implements EcoPlaceRepositoryJCustom {

    private final NamedParameterJdbcTemplate template;

    public EcoPlaceRepositoryJCustomImpl(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public EcoPlace findNearestEcoPlace(Position pos) {
        String sql = """
                SELECT eco_place_id,
                       name,
                       category,
                       lot_number_address,
                       road_address,
                       lat,
                       lng,
                       url,
                       image_url,
                       created_at,
                       updated_at,
                       (6371 * ACOS(COS(RADIANS(:lat)) * COS(RADIANS(lat)) * COS(RADIANS(lng) - RADIANS(:lng)) +
                                    SIN(RADIANS(:lat)) * SIN(RADIANS(lat)))) AS distance
                FROM eco_place
                ORDER BY distance
                LIMIT 1;
                """;

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("lat", pos.getLat())
                .addValue("lng", pos.getLng());

        return template.queryForObject(sql, params, ecoPlaceRowMapper());
    }

    @Override
    public EcoPlace findNearestEcoPlace(Position pos1, Position pos2) {
        String sql = """
                SELECT eco_place_id,
                       name,
                       category,
                       lot_number_address,
                       road_address,
                       lat,
                       lng,
                       url,
                       image_url,
                       created_at,
                       updated_at,
                       (6371 * ACOS(COS(RADIANS(:lat1)) * COS(RADIANS(lat)) * COS(RADIANS(lng) - RADIANS(:lng1)) +
                                    SIN(RADIANS(:lat1)) * SIN(RADIANS(lat)))) AS distance1,
                       (6371 * ACOS(COS(RADIANS(:lat2)) * COS(RADIANS(lat)) * COS(RADIANS(lng) - RADIANS(:lng2)) +
                                    SIN(RADIANS(:lat2)) * SIN(RADIANS(lat)))) AS distance2
                FROM eco_place
                ORDER BY distance1 + distance2
                LIMIT 1;
                """;

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("lat1", pos1.getLat())
                .addValue("lng1", pos1.getLng())
                .addValue("lat2", pos2.getLat())
                .addValue("lng2", pos2.getLng());

        return template.queryForObject(sql, params, ecoPlaceRowMapper());
    }

    private RowMapper<EcoPlace> ecoPlaceRowMapper() {
        return (rs, rowNum) -> EcoPlace.of(
                rs.getLong("eco_place_id"),
                rs.getString("name"),
                PlaceCategory.valueOf(rs.getString("category")),
                rs.getString("lot_number_address"),
                rs.getString("road_address"),
                rs.getString("lat"),
                rs.getString("lng"),
                rs.getString("url"),
                rs.getString("image_url"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime()
        );
    }
}
