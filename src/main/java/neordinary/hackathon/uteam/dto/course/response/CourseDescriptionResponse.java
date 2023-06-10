package neordinary.hackathon.uteam.dto.course.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CourseDescriptionResponse {

    @Schema(description = "자동 생성된 내용", example = "이 코스는 너무 ...")
    private String content;

    public static CourseDescriptionResponse of(String content) {
        return new CourseDescriptionResponse(content);
    }
}
