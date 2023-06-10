package neordinary.hackathon.uteam.dto.like;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import neordinary.hackathon.uteam.domain.Like;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class LikeDto {

    private Long id;
    private Long userId;
    private Long courseId;

    public static LikeDto of(Long id, Long userId, Long courseId) {
        return new LikeDto(id, userId, courseId);
    }

    public static LikeDto from(Like entity) {
        return of(entity.getId(), entity.getUser().getId(), entity.getCourse().getId());
    }
}
