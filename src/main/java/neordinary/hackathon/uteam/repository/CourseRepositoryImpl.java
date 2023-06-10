package neordinary.hackathon.uteam.repository;

import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import neordinary.hackathon.uteam.domain.Course;

import java.util.List;

import static neordinary.hackathon.uteam.domain.QCourse.course;

@RequiredArgsConstructor
public class CourseRepositoryImpl implements CourseRepositoryQCustom {

    private final JPQLQueryFactory queryFactory;

    @Override
    public List<Course> searchByKeyword(String keyword) {
        return queryFactory.selectFrom(course)
                .where(course.description.likeIgnoreCase(keyword)
                        .or(course.name.likeIgnoreCase(keyword)))
                .fetch();
    }
}
