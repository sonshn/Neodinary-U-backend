package neordinary.hackathon.uteam.dto.place.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import neordinary.hackathon.uteam.constant.PlaceCategory;
import neordinary.hackathon.uteam.domain.Address;
import neordinary.hackathon.uteam.domain.Position;
import neordinary.hackathon.uteam.dto.place.PlaceDto;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PlaceResponse {

    @Schema(description = "PK of place", example = "2")
    private Long id;

    @Schema(description = "장소 이름", example = "카카오프렌즈 코엑스점")
    private String name;

    private PlaceCategory category;

    private Address address;

    private Position pos;

    @Schema(description = "상세페이지 주소", example = "http://place.map.daum.net/26338954")
    private String url;

    public static PlaceResponse of(Long id, String name, PlaceCategory category, Address address, Position pos, String url) {
        return new PlaceResponse(id, name, category, address, pos, url);
    }

    public static PlaceResponse from(PlaceDto dto) {
        return of(
                dto.getId(),
                dto.getName(),
                dto.getCategory(),
                dto.getAddress(),
                dto.getPos(),
                dto.getUrl()
        );
    }
}
