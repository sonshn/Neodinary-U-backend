package neordinary.hackathon.uteam.repository;

import neordinary.hackathon.uteam.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findBySocialUid(String socialUid);
}
