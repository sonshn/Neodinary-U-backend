package neordinary.hackathon.uteam.exception.course;

import neordinary.hackathon.uteam.exception.common.NotFoundException;

public class CourseNotFoundByIdException extends NotFoundException {
    public CourseNotFoundByIdException(Long id) {
        super("courseId=" + id);
    }
}
