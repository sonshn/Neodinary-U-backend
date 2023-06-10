package neordinary.hackathon.uteam.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import neordinary.hackathon.uteam.domain.UserHashtag;
import neordinary.hackathon.uteam.dto.userHashtag.UserHashtagDto;
import neordinary.hackathon.uteam.dto.userHashtag.UserHashtagRequest;
import neordinary.hackathon.uteam.dto.userHashtag.UserHashtagListResponse;
import neordinary.hackathon.uteam.repository.UserHashtagRepository;
import neordinary.hackathon.uteam.security.UserPrincipal;
import neordinary.hackathon.uteam.service.UserHashtagService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "UserHashtag")
@RequiredArgsConstructor
@RequestMapping("/user-hashtags")
@RestController
public class UserHashtagController {

    private final UserHashtagService userHashtagService;

    private final UserHashtagRepository userHashtagRepository;

    // TODO: DTO를 Response로 고쳐서 API 테스트
    @Operation(
            summary = "회원 가입할 때 유저가 해시태그를 선택",
            description = "해시태그를 등록했습니다!",
            security = @SecurityRequirement(name = "access-token")
    )
    @PostMapping
    public UserHashtagListResponse createUserHashtag(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody UserHashtagRequest request
    ){
        Long Id = userPrincipal.getUserId();

        List<UserHashtagDto> userHashtagDtoList = userHashtagService.createHashtagDto(Id, request);

        return UserHashtagListResponse.of(userHashtagDtoList);
    }

    @Operation(
            summary = "회원 가입할 때 선택한 해시태그 목록을 마이 페이지에서 조회",
            description = "사용자의 해시태그는 다음과 같습니다!",
            security = @SecurityRequirement(name = "access-token")
    )
    @GetMapping
    public UserHashtagListResponse findUserHashtag(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ){
        Long Id = userPrincipal.getUserId();

        return UserHashtagListResponse.of(userHashtagService.findAllByUserId(Id));
    }
}
