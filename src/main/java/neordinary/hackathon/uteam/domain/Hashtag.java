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
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_id")
    private Long id;

    @JoinColumn(name = "course_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @Column(nullable = false)
    private String tag;

    public static Hashtag of(String tag) {
        return new Hashtag(null, null, tag);
    }

    public static Hashtag of(Long id, Course course, String tag) {
        return new Hashtag(id, course, tag);
    }

    protected void setCourse(Course course) {
        this.course = course;
    }
}
