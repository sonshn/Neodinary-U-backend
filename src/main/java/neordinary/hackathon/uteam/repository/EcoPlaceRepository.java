package neordinary.hackathon.uteam.repository;

import neordinary.hackathon.uteam.domain.EcoPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EcoPlaceRepository extends JpaRepository<EcoPlace, Long>, EcoPlaceRepositoryJCustom {
}
