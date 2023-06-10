package neordinary.hackathon.uteam.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class PointHistory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_history_id")
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private Integer point;

    public static PointHistory of(User user, String reason, Integer point){
        return new PointHistory(null, user, reason, point, null, null);
    }

    public static PointHistory of(Long id, User user, String reason, Integer point,
                                  LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new PointHistory(id, user, reason, point, createdAt, updatedAt);
    }

    private PointHistory(Long id, User user, String reason, Integer point, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.user = user;
        this.reason = reason;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
