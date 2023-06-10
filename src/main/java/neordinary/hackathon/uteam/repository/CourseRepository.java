package neordinary.hackathon.uteam.repository;

import neordinary.hackathon.uteam.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
