package neordinary.hackathon.uteam.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @JoinColumn(name = "course_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Double rate;

    public static Review of(Course course, User user, String content, Double rate) {
        return new Review(null, course, user, content, rate, null, null);
    }

    public static Review of(Long id, Course course, User user, String content, Double rate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Review(id, course, user, content, rate, createdAt, updatedAt);
    }

    private Review(Long id, Course course, User user, String content, Double rate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.course = course;
        this.user = user;
        this.content = content;
        this.rate = rate;
    }
}
