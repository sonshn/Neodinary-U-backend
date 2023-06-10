package neordinary.hackathon.uteam.service;

import lombok.RequiredArgsConstructor;
import neordinary.hackathon.uteam.domain.Course;
import neordinary.hackathon.uteam.domain.Like;
import neordinary.hackathon.uteam.domain.User;
import neordinary.hackathon.uteam.dto.like.LikeDto;
import neordinary.hackathon.uteam.repository.LikeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class LikeService {

    private final UserService userService;
    private final CourseService courseService;
    private final LikeRepository likeRepository;

    @Transactional
    public LikeDto likeToCourse(Long userId, Long courseId) {
        validateAlreadyNotExistsLike(userId, courseId);

        User user = userService.findById(userId);
        Course course = courseService.findById(courseId);
        Like like = likeRepository.save(Like.of(user, course));
        return LikeDto.from(like);
    }

    private void validateAlreadyNotExistsLike(Long userId, Long courseId) {
        if (likeRepository.existsByUser_IdAndCourse_Id(userId, courseId)) {
            throw new AlreadyLikedCourseException();
        }
    }
}
