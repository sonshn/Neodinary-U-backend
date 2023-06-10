package neordinary.hackathon.uteam.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "likes")
@Entity
public class Like extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "course_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    public static Like of(User user, Course course) {
        return of(null, user, course, null, null);
    }

    public static Like of(Long id, User user, Course course, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Like(id, user, course, createdAt, updatedAt);
    }

    private Like(Long id, User user, Course course, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.user = user;
        this.course = course;
    }
}
