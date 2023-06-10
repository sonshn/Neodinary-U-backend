package neordinary.hackathon.uteam.service;

import lombok.RequiredArgsConstructor;
import neordinary.hackathon.uteam.domain.User;
import neordinary.hackathon.uteam.domain.UserHashtag;
import neordinary.hackathon.uteam.dto.userHashtag.UserHashtagDto;
import neordinary.hackathon.uteam.repository.UserHashtagRepository;
import neordinary.hackathon.uteam.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserHashtagService {

    // private final UserRepository userRepository;
    private final UserHashtagRepository

    @Transactional
    public List<UserHashtag> getUserHashtagList(User user) {
        List<UserHashtag> userHashtagList = plantRepository.findAllByPlaceOrderByRemainderDateDesc(place);

        return userHashtagList;
    }
}
