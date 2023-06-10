package neordinary.hackathon.uteam.repository;

import neordinary.hackathon.uteam.domain.UserHashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHashtagRepository extends JpaRepository<UserHashtag, Long> {
}
