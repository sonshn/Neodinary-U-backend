package neordinary.hackathon.uteam.dto.course.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import neordinary.hackathon.uteam.dto.place.request.PlaceRequest;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class CourseCreateRequest {

    @Schema(description = "<p>추천 장소 여부." +
            "<p>추천 장소라면 <code>true</code>, 추천 장소가 아니라면 <code>false</code>이다.",
            example = "false")
    @NotNull
    private Boolean isRecommended;

    @Schema(description = "코스 이름", example = "A코스")
    @NotEmpty
    private String name;

    @Schema(description = "코스 설명", example = "이 코스는 ...")
    @NotEmpty
    private String description;

    @Schema(description = "장소 정보")
    private List<PlaceRequest> places;

    @Schema(description = "해시태그 목록", example = "[\"#친환경\", \"#제로웨이스트\", \"#저렴한\"]")
    private List<String> hashtags;
}
