package neordinary.hackathon.uteam.dto.point;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PointHistoryResponse {

    private Long id;
    private String reason;
    private Integer point;

    public static PointHistoryResponse of(Long id, String reason, Integer point) {
        return new PointHistoryResponse(id, reason, point);
    }

    public static PointHistoryResponse from(PointHistoryDto dto) {
        return of(
                dto.getId(),
                dto.getReason(),
                dto.getPoint()
        );
    }
}
