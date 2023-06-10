package neordinary.hackathon.uteam.repository;

import neordinary.hackathon.uteam.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    boolean existsByUser_IdAndCourse_Id(Long userId, Long courseId);

    Optional<Like> findByUser_IdAndCourse_Id(Long userId, Long courseId);
}
