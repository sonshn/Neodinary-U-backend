package neordinary.hackathon.uteam.dto.course;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import neordinary.hackathon.uteam.domain.Course;
import neordinary.hackathon.uteam.dto.hashtag.HashtagDto;
import neordinary.hackathon.uteam.dto.place.PlaceDto;
import neordinary.hackathon.uteam.dto.review.ReviewDto;
import neordinary.hackathon.uteam.dto.user.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CourseDto {

    private Long id;
    private UserDto userDto;
    private String name;
    private String description;
    private List<PlaceDto> placeDtos;
    private List<HashtagDto> hashtagDtos;
    private List<ReviewDto> reviewDtos;

    public static CourseDto of(Long id, UserDto userDto, String name, String description, List<PlaceDto> placeDtos, List<HashtagDto> hashtagDtos, List<ReviewDto> reviewDtos) {
        return new CourseDto(id, userDto, name, description, placeDtos, hashtagDtos, reviewDtos);
    }

    public static CourseDto from(Course entity) {
        return of(
                entity.getId(),
                UserDto.from(entity.getUser()),
                entity.getName(),
                entity.getDescription(),
                entity.getPlaces().stream()
                        .map(PlaceDto::from)
                        .collect(Collectors.toUnmodifiableList()),
                entity.getHashtags().stream()
                        .map(HashtagDto::from)
                        .collect(Collectors.toUnmodifiableList()),
                entity.getReviews().stream()
                        .map(ReviewDto::from)
                        .collect(Collectors.toUnmodifiableList())
        );
    }
}
