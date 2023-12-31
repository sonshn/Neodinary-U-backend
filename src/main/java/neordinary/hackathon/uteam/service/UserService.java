package neordinary.hackathon.uteam.service;

import lombok.RequiredArgsConstructor;
import neordinary.hackathon.uteam.domain.User;
import neordinary.hackathon.uteam.dto.user.UserDto;
import neordinary.hackathon.uteam.exception.user.UserNotFoundByIdException;
import neordinary.hackathon.uteam.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserDto createUser(UserDto userDto) {
        return UserDto.from(userRepository.save(userDto.toEntity()));
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundByIdException(userId));
    }

    public Optional<UserDto> findOptDtoBySocialUid(String socialUid) {
        return userRepository.findBySocialUid(socialUid).map(UserDto::from);
    }

    @Cacheable(value = "user", key = "#userId")
    public UserDto findDtoById(Long userId) {
        return UserDto.from(findById(userId));
    }

    @Cacheable(value = "user", key = "'point-ranking'")
    public List<UserDto> findDtosOrderByPointRanking() {
        return userRepository.findByOrderByPointDesc().stream()
                .map(UserDto::from)
                .toList();
    }
}
