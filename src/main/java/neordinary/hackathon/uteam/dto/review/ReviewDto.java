package neordinary.hackathon.uteam.dto.review;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import neordinary.hackathon.uteam.domain.Review;
import neordinary.hackathon.uteam.dto.user.UserDto;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ReviewDto {

    private Long id;
    private Long courseId;
    private UserDto userDto;
    private String content;
    private Double rate;

    public static ReviewDto of(Long id, Long courseId, UserDto userDto, String content, Double rate) {
        return new ReviewDto(id, courseId, userDto, content, rate);
    }

    public static ReviewDto from(Review entity) {
        return of(
                entity.getId(),
                entity.getCourse().getId(),
                UserDto.from(entity.getUser()),
                entity.getContent(),
                entity.getRate()
        );
    }
}
