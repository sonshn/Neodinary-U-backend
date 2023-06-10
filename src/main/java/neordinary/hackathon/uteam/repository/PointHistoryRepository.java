package neordinary.hackathon.uteam.repository;

import neordinary.hackathon.uteam.domain.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {

    List<PointHistory> findAllByUser_Id(Long userId);
}
