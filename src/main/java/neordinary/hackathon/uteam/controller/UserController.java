package neordinary.hackathon.uteam.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import neordinary.hackathon.uteam.dto.user.response.UserListResponse;
import neordinary.hackathon.uteam.dto.user.response.UserResponse;
import neordinary.hackathon.uteam.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "User")
@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "포인트 랭킹 정보 조회",
            description = "포인트가 높은 순으로 전체 유저를 조회합니다.",
            security = @SecurityRequirement(name = "access-token")
    )
    @GetMapping("/point-rank")
    public UserListResponse getUserListByPointRanking() {
        List<UserResponse> userResponses = userService.findDtosOrderByPointRanking().stream()
                .map(UserResponse::from)
                .toList();
        return UserListResponse.of(userResponses);
    }
}
