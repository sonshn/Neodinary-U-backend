package neordinary.hackathon.uteam.service;

import lombok.RequiredArgsConstructor;
import neordinary.hackathon.uteam.domain.User;
import neordinary.hackathon.uteam.domain.UserHashtag;
import neordinary.hackathon.uteam.dto.userHashtag.UserHashtagDto;
import neordinary.hackathon.uteam.dto.userHashtag.UserHashtagRequest;
import neordinary.hackathon.uteam.repository.UserHashtagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserHashtagService {

    private final UserService userService;

    private final UserHashtagRepository userHashtagRepository;

    @Transactional
    public List<UserHashtagDto> createHashtagDto(Long userId, UserHashtagRequest request) {

        User user = userService.findById(userId);

        List<UserHashtagDto> userHashtagDtos = request.getTags().stream()
                .map(tag -> {
                    String hashtagRemovedSharp = tag.substring(1);
                    UserHashtag userHashtag = userHashtagRepository.save(UserHashtag.of(hashtagRemovedSharp));
                    user.addUserHashtag(userHashtag);
                    return userHashtag;
                })
                .map(UserHashtagDto::from)
                .collect(Collectors.toUnmodifiableList());

        return userHashtagDtos;
    }
}