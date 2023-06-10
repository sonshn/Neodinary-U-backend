package neordinary.hackathon.uteam.dto.course.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CourseListResponse {

    private List<CourseResponse> courses;

    public static CourseListResponse of(List<CourseResponse> courses) {
        return new CourseListResponse(courses);
    }
}
