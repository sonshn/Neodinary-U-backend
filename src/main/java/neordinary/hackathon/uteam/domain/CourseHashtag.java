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
public class CourseHashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_hashtag_id")
    private Long id;

    @JoinColumn(name = "course_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @JoinColumn(name = "hashtag_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Hashtag hashtag;

    public static CourseHashtag of(Course course, Hashtag hashtag) {
        return new CourseHashtag(null, course, hashtag);
    }

    public static CourseHashtag of(Long id, Course course, Hashtag hashtag) {
        return new CourseHashtag(id, course, hashtag);
    }
}
