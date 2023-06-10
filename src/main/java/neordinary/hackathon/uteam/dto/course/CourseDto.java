package neordinary.hackathon.uteam.dto.course;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import neordinary.hackathon.uteam.domain.Course;
import neordinary.hackathon.uteam.dto.hashtag.HashtagDto;
import neordinary.hackathon.uteam.dto.place.PlaceDto;
import neordinary.hackathon.uteam.dto.user.UserDto;

import java.util.List;

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

    public static CourseDto of(Long id, UserDto userDto, String name, String description, List<PlaceDto> placeDtos, List<HashtagDto> hashtagDtos) {
        return new CourseDto(id, userDto, name, description, placeDtos, hashtagDtos);
    }

    public static CourseDto from(Course entity) {
        return of(
                entity.getId(),
                UserDto.from(entity.getUser()),
                entity.getName(),
                entity.getDescription(),
                entity.getPlaces().stream()
                        .map(PlaceDto::from)
                        .toList(),
                entity.getHashtags().stream()
                        .map(HashtagDto::from)
                        .toList()
        );
    }
}
