package neordinary.hackathon.uteam.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class UserHashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_hashtag_id")
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private String tag;

    public static UserHashtag of(String tag) {
        return new UserHashtag(null, null, tag);
    }

    public static UserHashtag of(Long id, User user, String tag) {
        return new UserHashtag(id, user, tag);
    }

    protected void setUser(User user) {
        this.user = user;
    }
}
