package neordinary.hackathon.uteam.dto.place.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import neordinary.hackathon.uteam.constant.PlaceCategory;
import neordinary.hackathon.uteam.domain.EcoPlace;
import neordinary.hackathon.uteam.dto.place.request.PlaceRequest;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PlaceResponseForRecommendedCourse {

    @Schema(description = "<p>추천 장소 여부." +
            "<p>추천 장소라면 <code>true</code>, 추천 장소가 아니라면 <code>false</code>이다.",
            example = "false")
    @NotNull
    private Boolean isRecommended;

    @Schema(description = "Kakao에서 응답받은 장소의 id", example = "26338954")
    @Nullable
    private String kakaoPid;

    @Schema(description = "장소 이름", example = "카카오프렌즈 코엑스점")
    private String name;

    private PlaceCategory category;

    @Schema(description = "지번주소", example = "서울 강남구 삼성동 159")
    private String lotNumberAddress;

    @Schema(description = "도로명주소", example = "서울 강남구 영동대로 513")
    private String roadAddress;

    @Schema(description = "위도", example = "37.51207412593136")
    @NotEmpty
    private String lat;

    @Schema(description = "경도", example = "127.05902969025047")
    @NotEmpty
    private String lng;

    @Schema(description = "상세페이지 주소", example = "http://place.map.daum.net/26338954")
    private String url;

    public static PlaceResponseForRecommendedCourse of(Boolean isRecommended, @Nullable String kakaoPid, String name, PlaceCategory category, String lotNumberAddress, String roadAddress, String lat, String lng, String url) {
        return new PlaceResponseForRecommendedCourse(isRecommended, kakaoPid, name, category, lotNumberAddress, roadAddress, lat, lng, url);
    }

    public static PlaceResponseForRecommendedCourse from(PlaceRequest place) {
        return of(
                false,
                place.getKakaoPid(),
                place.getName(),
                place.getCategory(),
                place.getLotNumberAddress(),
                place.getRoadAddress(),
                place.getLat(),
                place.getLng(),
                place.getUrl()
        );
    }

    public static PlaceResponseForRecommendedCourse from(EcoPlace place) {
        return of(
                true,
                null,
                place.getName(),
                place.getCategory(),
                place.getLotNumberAddress(),
                place.getRoadAddress(),
                place.getLat(),
                place.getLng(),
                place.getUrl()
        );
    }
}
