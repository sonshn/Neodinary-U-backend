package neordinary.hackathon.uteam.dto.user.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import neordinary.hackathon.uteam.constant.user.LoginType;
import neordinary.hackathon.uteam.dto.file.response.ImageResponse;
import neordinary.hackathon.uteam.dto.user.UserDto;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class LoggedInUserResponse {

    @Schema(description = "회원의 id(PK)", example = "1")
    private Long id;

    @Schema(description = "프로필 이미지")
    private ImageResponse image;

    @Schema(description = "로그인 유형", example = "KAKAO")
    private LoginType loginType;

    @Schema(description = "이름", example = "우기")
    private String name;

    public static LoggedInUserResponse from(UserDto userDto) {
        return new LoggedInUserResponse(
                userDto.getId(),
                ImageResponse.of(userDto.getProfileImageUrl(), userDto.getProfileThumbnailImageUrl()),
                userDto.getLoginType(),
                userDto.getName()
        );
    }
}
