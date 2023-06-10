package neordinary.hackathon.uteam.dto.point;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import neordinary.hackathon.uteam.domain.PointHistory;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PointHistoryDto {

    private Long id;
    private Long userId;
    private String reason;
    private Integer point;

    public static PointHistoryDto of(Long id, Long userId, String reason, Integer point){
        return new PointHistoryDto(id, userId, reason, point);
    }

    public static PointHistoryDto from(PointHistory entity){
        return of(
                entity.getId(),
                entity.getUser().getId(),
                entity.getReason(),
                entity.getPoint()
        );
    }
}
