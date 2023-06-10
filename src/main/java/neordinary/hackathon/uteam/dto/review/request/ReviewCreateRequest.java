package neordinary.hackathon.uteam.dto.review.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class ReviewCreateRequest {

    @Schema(description = "후기 내용", example = "이 코스는 정말이지 ...")
    @NotEmpty
    private String content;

    @Schema(description = "<p>후기.<p> 0.5점부터 5점까지0.5단위로 줄 수 있다.",
            example = "3.5")
    @Min(0)
    @NotNull
    private Double rate;
}
