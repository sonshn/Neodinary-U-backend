package neordinary.hackathon.uteam.service;

import lombok.RequiredArgsConstructor;
import neordinary.hackathon.uteam.domain.Course;
import neordinary.hackathon.uteam.domain.Hashtag;
import neordinary.hackathon.uteam.domain.Place;
import neordinary.hackathon.uteam.domain.User;
import neordinary.hackathon.uteam.dto.course.CourseDto;
import neordinary.hackathon.uteam.dto.course.request.CourseCreateRequest;
import neordinary.hackathon.uteam.dto.place.PlaceDto;
import neordinary.hackathon.uteam.repository.CourseRepository;
import neordinary.hackathon.uteam.repository.HashtagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            if (!placeReq.getIsRecommended()) {
                Place place = placeService.save(placeReq);
                course.addPlace(place);
            } else {
                // TODO: 추천 장소인 경우에 대한 로직 구현
            }
        });

        request.getHashtags().forEach(tag -> {
            String hashtagRemovedSharp = tag.substring(1);
            Hashtag hashtag = hashtagRepository.save(Hashtag.of(hashtagRemovedSharp));
            course.addHashtag(hashtag);
        });

        return CourseDto.from(course);
    }
}
