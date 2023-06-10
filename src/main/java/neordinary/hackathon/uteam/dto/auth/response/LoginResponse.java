package neordinary.hackathon.uteam.dto.auth.response;

import neordinary.hackathon.uteam.dto.user.response.LoggedInUserResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class LoginResponse {

    @Schema(description = "로그인 유저 정보")
    LoggedInUserResponse loggedInMember;

    @Schema(description = "Token 정보")
    TokenResponse tokens;

    public static LoginResponse of(LoggedInUserResponse loggedInMember, TokenResponse tokens) {
        return new LoginResponse(loggedInMember, tokens);
    }
}
