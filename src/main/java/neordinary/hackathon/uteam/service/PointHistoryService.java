package neordinary.hackathon.uteam.service;

import lombok.RequiredArgsConstructor;
import neordinary.hackathon.uteam.domain.PointHistory;
import neordinary.hackathon.uteam.domain.User;
import neordinary.hackathon.uteam.dto.point.PointHistoryDto;
import neordinary.hackathon.uteam.dto.userHashtag.UserHashtagDto;
import neordinary.hackathon.uteam.repository.PointHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PointHistoryService {

    private final UserService userService;
    private final PointHistoryRepository pointHistoryRepository;

    @Transactional
    public PointHistoryDto save(Long userId, String reason, Integer point) {
        User user = userService.findById(userId);
        return PointHistoryDto.from(pointHistoryRepository.save(PointHistory.of(user, reason, point)));
    }

    public List<PointHistoryDto> findAllByUserId(Long id) {
        return pointHistoryRepository.findAllByUser_Id(id).stream()
                .map(PointHistoryDto::from)
                .collect(Collectors.toUnmodifiableList());
    }
}
