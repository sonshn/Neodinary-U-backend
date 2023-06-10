package neordinary.hackathon.uteam.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import neordinary.hackathon.uteam.dto.review.ReviewDto;
import neordinary.hackathon.uteam.dto.review.request.ReviewCreateRequest;
import neordinary.hackathon.uteam.dto.review.response.ReviewResponse;
import neordinary.hackathon.uteam.security.UserPrincipal;
import neordinary.hackathon.uteam.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Tag(name = "Review(후기)")
@RequiredArgsConstructor
@RequestMapping("/reviews")
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(
            summary = "코스에 후기 작성하기",
            description = "<p><code>courseId</code>에 해당하는 코스에 후기를 작성한다.",
            security = @SecurityRequirement(name = "access-token")
    )
    @PostMapping("/courses/{courseId}")
    public ResponseEntity<ReviewResponse> addReviewToCourse(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @Parameter(description = "후기를 추가하고자 하는 코스의 id(PK)", example = "3") @PathVariable Long courseId,
            @Valid @RequestBody ReviewCreateRequest reviewCreateRequest
    ) {
        ReviewDto reviewDto = reviewService.addReviewToCourse(userPrincipal.getUserId(), courseId, reviewCreateRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(URI.create("/reviews/" + reviewDto.getId()))
                .body(ReviewResponse.from(reviewDto));
    }
}
