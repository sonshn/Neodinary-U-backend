package neordinary.hackathon.uteam.repository;

import neordinary.hackathon.uteam.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
