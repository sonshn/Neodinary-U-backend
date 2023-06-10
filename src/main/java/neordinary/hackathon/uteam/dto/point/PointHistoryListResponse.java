package neordinary.hackathon.uteam.dto.point;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PointHistoryListResponse {

    private List<PointHistoryResponse> pointHistories;

    public static PointHistoryListResponse of(List<PointHistoryResponse> pointHistorys) {
        return new PointHistoryListResponse(pointHistorys);
    }

}
