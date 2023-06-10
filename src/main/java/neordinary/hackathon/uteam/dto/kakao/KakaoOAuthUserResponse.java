package neordinary.hackathon.uteam.dto.kakao;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import neordinary.hackathon.uteam.constant.ConstantUtils;
import neordinary.hackathon.uteam.constant.user.LoginType;
import neordinary.hackathon.uteam.dto.user.UserDto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@SuppressWarnings("unchecked")  // TODO: Map -> Object 변환 로직이 있어서 generic type casting 문제를 무시한다. 더 좋은 방법이 있다면 고려할 수 있음.
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class KakaoOAuthUserResponse {

    private String id;
    private LocalDateTime connectedAt;
    private Map<String, Object> properties;
    private KakaoAccount kakaoAccount;

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public static class KakaoAccount {

        private Boolean profileNicknameNeedsAgreement;
        private Boolean profileImageNeedsAgreement;
        private Profile profile;

        @AllArgsConstructor(access = AccessLevel.PRIVATE)
        @Getter
        public static class Profile {

            private String nickname;
            private String thumbnailImageUrl;
            private String profileImageUrl;

            public static Profile from(Map<String, Object> attributes) {
                Object thumbnailImageUrl = attributes.get("thumbnail_image_url");
                Object profileImageUrl = attributes.get("profile_image_url");

                return new Profile(
                        attributes.get("nickname").toString(),
                        thumbnailImageUrl == null ? null : thumbnailImageUrl.toString(),
                        profileImageUrl == null ? null : profileImageUrl.toString()
                );
            }
        }

        public static KakaoAccount from(Map<String, Object> attributes) {
            return new KakaoAccount(
                    Boolean.valueOf(String.valueOf(attributes.get("profile_nickname_needs_agreement"))),
                    Boolean.valueOf(String.valueOf(attributes.get("profile_image_needs_agreement"))),
                    Profile.from((Map<String, Object>) attributes.get("profile"))
            );
        }
    }

    public static KakaoOAuthUserResponse from(Map<String, Object> attributes) {
        return new KakaoOAuthUserResponse(
                String.valueOf(attributes.get("id")),
                ZonedDateTime.parse(
                        String.valueOf(attributes.get("connected_at")),
                        DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.systemDefault())
                ).toLocalDateTime(),
                (Map<String, Object>) attributes.get("properties"),
                KakaoAccount.from((Map<String, Object>) attributes.get("kakao_account"))
        );
    }

    public UserDto toUserDto() {
        String profileImageUrl = getProfileImageUrl();
        if (profileImageUrl == null) {
            profileImageUrl = ConstantUtils.defaultProfileImageUrl;
        }

        String thumbnailImageUrl = getThumbnailImageUrl();
        if (thumbnailImageUrl == null) {
            thumbnailImageUrl = ConstantUtils.defaultProfileThumbnailImageUrl;
        }

        return UserDto.of(
                this.getSocialUid(),
                LoginType.KAKAO,
                this.getNickname(),
                profileImageUrl,
                thumbnailImageUrl
        );
    }

    // Getter
    public String getSocialUid() {
        return this.getId();
    }

    public String getNickname() {
        return this.getKakaoAccount().getProfile().getNickname();
    }

    public String getThumbnailImageUrl() {
        return this.getKakaoAccount().getProfile().getThumbnailImageUrl();
    }

    public String getProfileImageUrl() {
        return this.getKakaoAccount().getProfile().getProfileImageUrl();
    }
}
