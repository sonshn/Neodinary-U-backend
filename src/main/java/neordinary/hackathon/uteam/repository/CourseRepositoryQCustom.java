package neordinary.hackathon.uteam.repository;

import neordinary.hackathon.uteam.domain.Course;

import java.util.List;

public interface CourseRepositoryQCustom {

    List<Course> searchByKeyword(String keyword);
}
