package neordinary.hackathon.uteam.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Course extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(length = 500, nullable = false)
    private String description;

    public static Course of(User user, String name, String description) {
        return new Course(null, user, name, description, null, null);
    }

    public static Course of(Long id, User user, String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Course(id, user, name, description, createdAt, updatedAt);
    }

    private Course(Long id, User user, String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.user = user;
        this.name = name;
        this.description = description;
    }
}