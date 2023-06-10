package neordinary.hackathon.uteam.repository;

import neordinary.hackathon.uteam.dto.redis.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
