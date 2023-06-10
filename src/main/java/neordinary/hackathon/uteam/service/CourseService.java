package neordinary.hackathon.uteam.service;

import lombok.RequiredArgsConstructor;
import neordinary.hackathon.uteam.domain.Course;
import neordinary.hackathon.uteam.domain.Hashtag;
import neordinary.hackathon.uteam.domain.Place;
import neordinary.hackathon.uteam.domain.User;
import neordinary.hackathon.uteam.dto.course.CourseDto;
import neordinary.hackathon.uteam.dto.course.request.CourseCreateRequest;
import neordinary.hackathon.uteam.exception.course.CourseNotFoundByIdException;
import neordinary.hackathon.uteam.repository.CourseRepository;
import neordinary.hackathon.uteam.repository.HashtagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CourseService {

    private final UserService userService;
    private final PlaceService placeService;
    private final CourseRepository courseRepository;
    private final HashtagRepository hashtagRepository;

    @Transactional
    public CourseDto createCourse(Long userId, CourseCreateRequest request) {
        User user = userService.findById(userId);

        Course course = courseRepository.save(Course.of(user, request.getName(), request.getDescription()));

        request.getPlaces().forEach(placeReq -> {
            Place place = placeService.save(placeReq.getIsRecommended(), placeReq);
            course.addPlace(place);
        });

        request.getHashtags().forEach(tag -> {
            String hashtagRemovedSharp = tag.substring(1);
            Hashtag hashtag = hashtagRepository.save(Hashtag.of(hashtagRemovedSharp));
            course.addHashtag(hashtag);
        });

        return CourseDto.from(course);
    }

    public Course findById(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundByIdException(courseId));
    }

    public CourseDto findDtoById(Long courseId) {
        return CourseDto.from(findById(courseId));
    }
}
