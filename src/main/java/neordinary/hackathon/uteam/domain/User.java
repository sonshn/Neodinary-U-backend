package neordinary.hackathon.uteam.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import neordinary.hackathon.uteam.constant.user.LoginType;
import neordinary.hackathon.uteam.constant.user.RoleType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "users")
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String socialUid;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String profileImageUrl;

    @Column(nullable = false)
    private String profileThumbnailImageUrl;

    @Column(nullable = false)
    private Integer point;

    @OneToMany(mappedBy = "user")
    private List<UserHashtag> userHashtags = new LinkedList<>();

    public static User of(String socialUid, LoginType loginType, String name, String profileImageUrl, String profileThumbnailImageUrl) {
        return of(null, socialUid, loginType, RoleType.USER, name, profileImageUrl, profileThumbnailImageUrl, 0, null, null);
    }

    public static User of(Long id, String socialUid, LoginType loginType, RoleType role, String name, String profileImageUrl, String profileThumbnailImageUrl, Integer point, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new User(id, socialUid, loginType, role, name, profileImageUrl, profileThumbnailImageUrl, point, createdAt, updatedAt);
    }

    public User(Long id, String socialUid, LoginType loginType, RoleType role, String name, String profileImageUrl, String profileThumbnailImageUrl, Integer point, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.socialUid = socialUid;
        this.loginType = loginType;
        this.role = role;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.profileThumbnailImageUrl = profileThumbnailImageUrl;
        this.point = point;
    }

    public void addUserHashtag(UserHashtag userHashtag) {
        userHashtag.setUser(this);
        this.getUserHashtags().add(userHashtag);
    }
}
