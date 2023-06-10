package neordinary.hackathon.uteam.dto.hashtag;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import neordinary.hackathon.uteam.domain.Hashtag;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class HashtagDto {

    private Long id;
    private Long courseId;
    private String tag;

    public static HashtagDto of(Long id, Long courseId, String tag) {
        return new HashtagDto(id, courseId, tag);
    }

    public static HashtagDto from(Hashtag entity) {
        return of(
                entity.getId(),
                entity.getCourse().getId(),
                entity.getTag()
        );
    }
}
