package neordinary.hackathon.uteam.dto.user.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import neordinary.hackathon.uteam.dto.file.response.ImageResponse;
import neordinary.hackathon.uteam.dto.user.UserDto;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserResponse {

    @Schema(description = "PK of user", example = "1")
    private Long id;

    @Schema(description = "name of user", example = "재욱")
    private String name;

    private ImageResponse images;

    @Schema(description = "보유 포인트", example = "10")
    private Integer point;

    public static UserResponse of(Long id, String name, ImageResponse images, Integer point) {
        return new UserResponse(id, name, images, point);
    }

    public static UserResponse from(UserDto dto) {
        return of(
                dto.getId(),
                dto.getName(),
                ImageResponse.of(dto.getProfileImageUrl(), dto.getProfileThumbnailImageUrl()),
                dto.getPoint()
        );
    }
}
