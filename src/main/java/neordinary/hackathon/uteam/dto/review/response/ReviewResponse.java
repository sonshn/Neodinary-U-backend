package neordinary.hackathon.uteam.dto.review.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import neordinary.hackathon.uteam.dto.review.ReviewDto;
import neordinary.hackathon.uteam.dto.user.response.UserResponse;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ReviewResponse {

    @Schema(description = "PK of review", example = "5")
    private Long id;

    @Schema(description = "후기 작성자 정보")
    private UserResponse writer;

    @Schema(description = "후기 내용", example = "이 코스를 직접 해봤는데요 ...")
    private String content;

    @Schema(description = "<p>후기." +
            "<p>0.5점부터 5점까지 0.5 단위로 줄 수 있다.",
            example = "3.5")
    private Double rate;

    public static ReviewResponse of(Long id, UserResponse writer, String content, Double rate) {
        return new ReviewResponse(id, writer, content, rate);
    }

    public static ReviewResponse from(ReviewDto dto) {
        return of(
                dto.getId(),
                UserResponse.from(dto.getUserDto()),
                dto.getContent(),
                dto.getRate()
        );
    }
}
