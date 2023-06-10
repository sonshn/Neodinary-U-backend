package neordinary.hackathon.uteam.service;

import lombok.RequiredArgsConstructor;
import neordinary.hackathon.uteam.domain.*;
import neordinary.hackathon.uteam.dto.course.CourseDto;
import neordinary.hackathon.uteam.dto.course.request.CourseCreateRequest;
import neordinary.hackathon.uteam.repository.CourseRepository;
import neordinary.hackathon.uteam.repository.HashtagRepository;
import neordinary.hackathon.uteam.repository.PlaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CourseService {

    private final UserService userService;
    private final CourseRepository courseRepository;
    private final PlaceRepository placeRepository;
    private final HashtagRepository hashtagRepository;

    @Transactional
    public CourseDto createCourse(Long userId, CourseCreateRequest request) {
        User user = userService.findById(userId);

        Course course = courseRepository.save(Course.of(user, request.getName(), request.getDescription()));

        request.getPlaces().forEach(placeReq -> {
            Place place = placeRepository.save(Place.of(
                    placeReq.getKakaoPid(),
                    placeReq.getName(),
                    placeReq.getCategory(),
                    new Address(placeReq.getLotNumberAddress(), placeReq.getRoadAddress()),
                    new Position(placeReq.getLat(), placeReq.getLng()),
                    placeReq.getUrl()
            ));
            course.addPlace(place);
        });

        request.getHashtags().forEach(tag -> {
            String hashtagRemovedSharp = tag.substring(1);
            Hashtag hashtag = hashtagRepository.save(Hashtag.of(hashtagRemovedSharp));
            course.addHashtag(hashtag);
        });

        return CourseDto.from(course);
    }
}
