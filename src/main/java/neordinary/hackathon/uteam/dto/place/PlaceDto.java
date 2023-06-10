package neordinary.hackathon.uteam.dto.place;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import neordinary.hackathon.uteam.constant.PlaceCategory;
import neordinary.hackathon.uteam.domain.Address;
import neordinary.hackathon.uteam.domain.Place;
import neordinary.hackathon.uteam.domain.Position;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PlaceDto {

    private Long id;
    private Long courseId;
    private String kakaoPid;
    private String name;
    private PlaceCategory category;
    private Address address;
    private Position pos;
    private String url;

    public static PlaceDto of(Long id, Long courseId, String kakaoPid, String name, PlaceCategory category, Address address, Position pos, String url) {
        return new PlaceDto(id, courseId, kakaoPid, name, category, address, pos, url);
    }

    public static PlaceDto from(Place entity) {
        return of(
                entity.getId(),
                entity.getCourse().getId(),
                entity.getKakaoPid(),
                entity.getName(),
                entity.getCategory(),
                entity.getAddress(),
                entity.getPos(),
                entity.getUrl()
        );
    }
}
