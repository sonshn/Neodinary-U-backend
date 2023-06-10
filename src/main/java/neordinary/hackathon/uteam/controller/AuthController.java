package neordinary.hackathon.uteam.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import neordinary.hackathon.uteam.constant.user.LoginType;
import neordinary.hackathon.uteam.dto.auth.request.KakaoLoginRequest;
import neordinary.hackathon.uteam.dto.auth.request.TokenRefreshRequest;
import neordinary.hackathon.uteam.dto.auth.response.LoginResponse;
import neordinary.hackathon.uteam.dto.auth.response.TokenResponse;
import neordinary.hackathon.uteam.dto.kakao.KakaoOAuthUserResponse;
import neordinary.hackathon.uteam.dto.user.UserDto;
import neordinary.hackathon.uteam.dto.user.response.LoggedInUserResponse;
import neordinary.hackathon.uteam.service.JwtTokenService;
import neordinary.hackathon.uteam.service.KakaoService;
import neordinary.hackathon.uteam.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "로그인 등 인증 관련")
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final KakaoService kakaoService;
    private final UserService userService;
    private final JwtTokenService jwtTokenService;

    @Operation(
            summary = "카카오 로그인",
            description = "<p>Kakao에서 전달받은 access token으로 로그인합니다." +
                    "<p>로그인에 성공하면 로그인 사용자 정보, access token. refresh token을 응답합니다. 이후 로그인 원한이 필요한 API를 호출할 때는 HTTP header의 <strong>Authorization</strong>에 access token을 담아서 요청해야 합니다." +
                    "<p>Access token의 만료기한은 하루, refresh token의 만료기한은 1달입니다." +
                    "<p>이전에 탈퇴했던 회원이고 DB에서 완전히 삭제되지 않은 경우, 재가입을 진행합니다." +
                    "<p>사용자 정보에 포함된 약관 동의 정보(<code>termsInfo</code>)는 아직 약관 동의를 진행하지 않은 경우 <code>null</code>입니다."
    )
    @ApiResponses({
            @ApiResponse(description = "OK", responseCode = "200", content = @Content(schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(description = "[10401] 유효하지 않은 kakao access token으로 요청한 경우.", responseCode = "401", content = @Content)
    })
    @PostMapping("/login/kakao")
    public LoginResponse kakaoLogin(@Valid @RequestBody KakaoLoginRequest request) {
        KakaoOAuthUserResponse kakaoUserInfo = kakaoService.getUserInfo(request.getKakaoAccessToken());

        UserDto userDto = userService.findOptDtoBySocialUid(kakaoUserInfo.getSocialUid())
                .orElseGet(() -> userService.createUser(kakaoUserInfo.toUserDto()));

        TokenResponse tokenResponse = jwtTokenService.create(userDto.getId(), LoginType.KAKAO);

        return LoginResponse.of(LoggedInUserResponse.from(userDto), tokenResponse);
    }

    @Operation(
            summary = "토큰 갱신하기",
            description = "<p>기존 발급받은 refresh token으로 새로운 access token과 refresh token을 발급 받습니다."
    )
    @ApiResponses({
            @ApiResponse(description = "OK", responseCode = "200", content = @Content(schema = @Schema(implementation = TokenResponse.class))),
            @ApiResponse(description = "[1502] 유효하지 않은 token으로 요청한 경우. Token 값이 잘못되었거나 만료되어 유효하지 않은 경우로 token 갱신 필요", responseCode = "401", content = @Content),
    })
    @PostMapping("/token")
    public TokenResponse tokenRefresh(@Valid @RequestBody TokenRefreshRequest request) {
        return jwtTokenService.refresh(request.getRefreshToken());
    }
}
