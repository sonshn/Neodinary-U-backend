package neordinary.hackathon.uteam.dto.course.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import neordinary.hackathon.uteam.dto.place.request.PlaceRequest;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
public class CourseCreateRequest {

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
