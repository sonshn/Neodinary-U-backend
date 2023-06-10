package neordinary.hackathon.uteam.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import neordinary.hackathon.uteam.constant.PlaceCategory;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Place extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long id;

    @JoinColumn(name = "course_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    private String kakaoPid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PlaceCategory category;

    @Embedded
    private Address address;

    @Embedded
    private Point point;

    private String url;

    public static Place of(Course course, String kakaoPid, String name, PlaceCategory category, Address address, Point point, String url) {
        return new Place(null, course, kakaoPid, name, category, address, point, url, null, null);
    }

    public static Place of(Long id, Course course, String kakaoPid, String name, PlaceCategory category, Address address, Point point, String url, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Place(id, course, kakaoPid, name, category, address, point, url, createdAt, updatedAt);
    }

    private Place(Long id, Course course, String kakaoPid, String name, PlaceCategory category, Address address, Point point, String url, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.course = course;
        this.kakaoPid = kakaoPid;
        this.name = name;
        this.category = category;
        this.address = address;
        this.point = point;
        this.url = url;
    }
}
