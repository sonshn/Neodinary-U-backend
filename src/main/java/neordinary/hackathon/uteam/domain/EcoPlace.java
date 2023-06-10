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

    @Embedded
    private Address address;

    @Embedded
    private Point point;

    public static EcoPlace of(String name, PlaceCategory category, Address address, Point point) {
        return new EcoPlace(null, name, category, address, point, null, null);
    }

    public static EcoPlace of(Long id, String name, PlaceCategory category, Address address, Point point, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new EcoPlace(id, name, category, address, point, createdAt, updatedAt);
    }

    private EcoPlace(Long id, String name, PlaceCategory category, Address address, Point point, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.name = name;
        this.category = category;
        this.address = address;
        this.point = point;
    }
}
