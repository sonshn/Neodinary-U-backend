package neordinary.hackathon.uteam.repository;

import neordinary.hackathon.uteam.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
