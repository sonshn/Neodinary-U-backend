package neordinary.hackathon.uteam.constant;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "<p>장소 카테고리. 목록은 다음과 같음" +
        "<ul>" +
        "<li>RESTAURANT - 음식점</li>" +
        "<li>CAFE - 카페</li>" +
        "<li>ACCOMMONDATION - 숙박</li>" +
        "<li>ACTIVITY - 액티비티(관광명소, 문화시설)</li>" +
        "<li>ETC - 기타</li>" +
        "</ul>",
        example = "ETC")
public enum PlaceCategory {
    RESTAURANT,
    CAFE,
    ACCOMMONDATION,
    ACTIVITY,
    ETC,
}
