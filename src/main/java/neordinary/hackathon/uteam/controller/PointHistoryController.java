package neordinary.hackathon.uteam.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import neordinary.hackathon.uteam.dto.point.PointHistoryListResponse;
import neordinary.hackathon.uteam.dto.point.PointHistoryResponse;
import neordinary.hackathon.uteam.security.UserPrincipal;
import neordinary.hackathon.uteam.service.PointHistoryService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@Tag(name = "Point")
@RequiredArgsConstructor
@RequestMapping("/points")
@RestController
public class PointHistoryController {

    private final PointHistoryService pointHistoryService;

    @Operation(
            summary = "사용자의 포인트 내역 조회",
            description = "사용자의 포인트 내역은 다음과 같습니다!",
            security = @SecurityRequirement(name = "access-token")
    )
    @GetMapping
    public PointHistoryListResponse findUserPointHistory(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ){
        Long id = userPrincipal.getUserId();
        return PointHistoryListResponse.of(
                pointHistoryService.findAllByUserId(id).stream()
                        .map(PointHistoryResponse::from)
                        .collect(Collectors.toUnmodifiableList())
        );
    }
}
