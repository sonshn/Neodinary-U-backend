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
public class EcoPlace extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eco_place_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PlaceCategory category;

    private String lotNumberAddress;

    private String roadAddress;

    @Column(nullable = false)
    private String lat;

    @Column(nullable = false)
    private String lng;

    @Column
    private String url;

    @Column(nullable = false)
    private String imageUrl;

    public static EcoPlace of(Long id, String name, PlaceCategory category, String lotNumberAddress, String roadAddress, String lat, String lng, String url, String imageUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new EcoPlace(id, name, category, lotNumberAddress, roadAddress, lat, lng, url, imageUrl, createdAt, updatedAt);
    }

    private EcoPlace(Long id, String name, PlaceCategory category, String lotNumberAddress, String roadAddress, String lat, String lng, String url, String imageUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.name = name;
        this.category = category;
        this.lotNumberAddress = lotNumberAddress;
        this.roadAddress = roadAddress;
        this.lat = lat;
        this.lng = lng;
        this.url = url;
        this.imageUrl = imageUrl;
    }
}