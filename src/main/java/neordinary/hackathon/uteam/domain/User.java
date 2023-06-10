package neordinary.hackathon.uteam.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import neordinary.hackathon.uteam.constant.member.LoginType;
import neordinary.hackathon.uteam.constant.member.RoleType;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "users")
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
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
    private Integer point;

    public User of(String socialUid, LoginType loginType, String name) {
        return of(null, socialUid, loginType, RoleType.USER, name, 0, null, null);
    }

    public User of(Long id, String socialUid, LoginType loginType, RoleType role, String name, Integer point, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new User(id, socialUid, loginType, role, name, point, createdAt, updatedAt);
    }

    private User(Long id, String socialUid, LoginType loginType, RoleType role, String name, Integer point, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.loginType = loginType;
        this.socialUid = socialUid;
        this.role = role;
        this.name = name;
        this.point = point;
    }
}
