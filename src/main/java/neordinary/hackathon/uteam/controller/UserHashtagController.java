package neordinary.hackathon.uteam.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import neordinary.hackathon.uteam.dto.userHashtag.UserHashtagDto;
import neordinary.hackathon.uteam.dto.userHashtag.UserHashtagRequest;
import neordinary.hackathon.uteam.dto.userHashtag.UserHashtagListResponse;
import neordinary.hackathon.uteam.security.UserPrincipal;
import neordinary.hackathon.uteam.service.UserHashtagService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "UserHashtag")
@RequiredArgsConstructor
@RequestMapping("/user-hashtags")
@RestController
public class UserHashtagController {

    private final UserHashtagService userHashtagService;

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
}
