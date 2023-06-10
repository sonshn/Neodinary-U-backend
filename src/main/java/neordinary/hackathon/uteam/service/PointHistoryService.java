package neordinary.hackathon.uteam.service;

import lombok.RequiredArgsConstructor;
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

    private final PointHistoryRepository pointHistoryRepository;

    public List<PointHistoryDto> findAllByUserId(Long id) {
        return pointHistoryRepository.findAllByUser_Id(id).stream()
                .map(PointHistoryDto::from)
                .collect(Collectors.toUnmodifiableList());
    }
}
