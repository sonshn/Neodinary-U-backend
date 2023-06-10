package neordinary.hackathon.uteam.repository;

import neordinary.hackathon.uteam.domain.UserHashtag;
import neordinary.hackathon.uteam.dto.userHashtag.UserHashtagDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserHashtagRepository extends JpaRepository<UserHashtag, Long> {

    List<UserHashtag> findAllByUser_Id(Long userId);
}
