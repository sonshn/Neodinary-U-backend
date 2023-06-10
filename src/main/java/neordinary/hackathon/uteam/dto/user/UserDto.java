package neordinary.hackathon.uteam.dto.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import neordinary.hackathon.uteam.constant.user.LoginType;
import neordinary.hackathon.uteam.constant.user.RoleType;
import neordinary.hackathon.uteam.domain.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserDto {

    private Long id;
    private String socialUid;
    private LoginType loginType;
    private RoleType role;
    private String name;
    private String profileImageUrl;
    private String profileThumbnailImageUrl;
    private Integer point;

    public static UserDto of(String socialUid, LoginType loginType, String name, String profileImageUrl, String profileThumbnailImageUrl) {
        return of(null, socialUid, loginType, null, name, profileImageUrl, profileThumbnailImageUrl, null);
    }

    public static UserDto of(Long id, String socialUid, LoginType loginType, RoleType role, String name, String profileImageUrl, String profileThumbnailImageUrl, Integer point) {
        return new UserDto(id, socialUid, loginType, role, name, profileImageUrl, profileThumbnailImageUrl, point);
    }

    public static UserDto from(User entity) {
        return of(
                entity.getId(),
                entity.getSocialUid(),
                entity.getLoginType(),
                entity.getRole(),
                entity.getName(),
                entity.getProfileImageUrl(),
                entity.getProfileThumbnailImageUrl(),
                entity.getPoint()
        );
    }

    public User toEntity() {
        return User.of(
                this.getSocialUid(),
                this.getLoginType(),
                this.getName(),
                this.getProfileImageUrl(),
                this.getProfileThumbnailImageUrl()
        );
    }
}
