package neordinary.hackathon.uteam.dto.course.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import neordinary.hackathon.uteam.dto.course.CourseDto;
import neordinary.hackathon.uteam.dto.place.response.PlaceResponse;
import neordinary.hackathon.uteam.dto.review.response.ReviewResponse;
import neordinary.hackathon.uteam.dto.user.response.UserResponse;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CourseResponse {

    @Schema(description = "PK of course", example = "3")
    private Long id;

    @Schema(description = "유저 정보")
    private UserResponse uploader;

    @Schema(description = "코스 이름", example = "코스 A")
    private String name;

    @Schema(description = "코스 소개", example = "이 코스는 ...")
    private String description;

    @Schema(description = "코스의 장소 목록")
    private List<PlaceResponse> places;

    @Schema(description = "코스의 해시태그 목록")
    private List<String> hashtags;

    @Schema(description = "후기 목록")
    private List<ReviewResponse> reviews;

    public static CourseResponse of(Long id, UserResponse user, String name, String description, List<PlaceResponse> places, List<String> hashtags, List<ReviewResponse> reviews) {
        return new CourseResponse(id, user, name, description, places, hashtags, reviews);
    }

    public static CourseResponse from(CourseDto dto) {
        return of(
                dto.getId(),
                UserResponse.from(dto.getUserDto()),
                dto.getName(),
                dto.getDescription(),
                dto.getPlaceDtos().stream()
                        .map(PlaceResponse::from)
                        .collect(Collectors.toUnmodifiableList()),
                dto.getHashtagDtos().stream()
                        .map(hashtag -> "#" + hashtag.getTag())
                        .collect(Collectors.toUnmodifiableList()),
                dto.getReviewDtos().stream()
                        .map(ReviewResponse::from)
                        .collect(Collectors.toUnmodifiableList())
        );
    }
}
