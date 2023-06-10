package neordinary.hackathon.uteam.service;

import lombok.RequiredArgsConstructor;
import neordinary.hackathon.uteam.domain.User;
import neordinary.hackathon.uteam.dto.user.UserDto;
import neordinary.hackathon.uteam.exception.user.UserNotFoundByIdException;
import neordinary.hackathon.uteam.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundByIdException(userId));
    }

    public Optional<UserDto> findOptDtoBySocialUid(String socialUid) {
        return userRepository.findBySocialUid(socialUid).map(UserDto::from);
    }

    public UserDto findDtoById(Long userId) {
        return UserDto.from(findById(userId));
    }
}
