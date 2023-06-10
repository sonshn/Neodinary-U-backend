package neordinary.hackathon.uteam.repository;

import neordinary.hackathon.uteam.domain.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
}
