package neordinary.hackathon.uteam.dto.userHashtag;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import neordinary.hackathon.uteam.domain.Hashtag;
import neordinary.hackathon.uteam.domain.UserHashtag;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserHashtagDto {

    private Long id;
    private Long userId;
    private String tag;

    public static UserHashtagDto of(Long id, Long userId, String tag) {
        return new UserHashtagDto(id, userId, tag);
    }

    public static UserHashtagDto from(UserHashtag entity) {
        return of(
                entity.getId(),
                entity.getUser().getId(),
                entity.getTag()
        );
    }
}
