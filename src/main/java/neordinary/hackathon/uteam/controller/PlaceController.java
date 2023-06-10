package neordinary.hackathon.uteam.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import neordinary.hackathon.uteam.dto.place.response.RecommendedCoursePlacesResponse;
import neordinary.hackathon.uteam.service.PlaceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Place")
@RequiredArgsConstructor
@RequestMapping("/places")
@RestController
public class PlaceController {

    private final PlaceService placeService;

    @Operation(
            summary = "추천 장소를 포함한 코스 장소 리스트 조회",
            description = "보내준 장소 리스트에 추천 장소를 추가하여 응답합니다.",
            security = @SecurityRequirement(name = "access-token")
    )
    @PostMapping("/recommend")
    public RecommendedCoursePlacesResponse getRecommendedCoursePlaces(@RequestBody PlaceListRequest placeListRequest) {
        return RecommendedCoursePlacesResponse.of(placeService.getIncludeRecommendedPlaces(placeListRequest.getPlaces()));
    }
}
