package neordinary.hackathon.uteam.dto.redis;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@RedisHash(value = "refreshToken", timeToLive = 30 * 24 * 60 * 60)
public class RefreshToken {

    @Id
    private String token;
    private Long userId;

    public static RefreshToken of(String token, Long userId) {
        return new RefreshToken(token, userId);
    }
}
