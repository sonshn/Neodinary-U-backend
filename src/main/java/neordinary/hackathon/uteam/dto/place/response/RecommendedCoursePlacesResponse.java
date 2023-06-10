package neordinary.hackathon.uteam.dto.place.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RecommendedCoursePlacesResponse {

    private List<PlaceResponseForRecommendedCourse> places;

    public static RecommendedCoursePlacesResponse of(List<PlaceResponseForRecommendedCourse> places) {
        return new RecommendedCoursePlacesResponse(places);
    }
}
