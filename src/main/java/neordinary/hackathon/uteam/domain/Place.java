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

    @Column(nullable = false)
    private String imageUrl;

    @Embedded
    private Address address;

    @Embedded
    private Position pos;

    private String url;

    public static Place of(String kakaoPid, String name, PlaceCategory category, String imageUrl, Address address, Position pos, String url) {
        return new Place(null, kakaoPid, name, category, imageUrl, address, pos, url, null, null);
    }

    public static Place of(Long id, String kakaoPid, String name, PlaceCategory category, String imageUrl, Address address, Position pos, String url, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Place(id, kakaoPid, name, category, imageUrl, address, pos, url, createdAt, updatedAt);
    }

    private Place(Long id, String kakaoPid, String name, PlaceCategory category, String imageUrl, Address address, Position pos, String url, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.kakaoPid = kakaoPid;
        this.name = name;
        this.category = category;
        this.imageUrl = imageUrl;
        this.address = address;
        this.pos = pos;
        this.url = url;
    }

    protected void setCourse(Course course) {
        this.course = course;
    }
}
