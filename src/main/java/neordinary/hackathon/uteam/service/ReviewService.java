package neordinary.hackathon.uteam.service;

import lombok.RequiredArgsConstructor;
import neordinary.hackathon.uteam.domain.Course;
import neordinary.hackathon.uteam.domain.Review;
import neordinary.hackathon.uteam.domain.User;
import neordinary.hackathon.uteam.dto.review.ReviewDto;
import neordinary.hackathon.uteam.dto.review.request.ReviewCreateRequest;
import neordinary.hackathon.uteam.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewService {

    private final UserService userService;
    private final CourseService courseService;
    private final ReviewRepository reviewRepository;

    @Transactional
    public ReviewDto addReviewToCourse(Long userId, Long courseId, ReviewCreateRequest reviewRequest) {
        User user = userService.findById(userId);
        Course course = courseService.findById(courseId);

        Review review = Review.of(course, user, reviewRequest.getContent(), reviewRequest.getRate());
        reviewRepository.save(review);
        return ReviewDto.from(review);
    }
}
