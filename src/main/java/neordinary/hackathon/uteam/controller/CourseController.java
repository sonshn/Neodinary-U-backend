package neordinary.hackathon.uteam.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import neordinary.hackathon.uteam.dto.course.CourseDto;
import neordinary.hackathon.uteam.dto.course.request.CourseCreateRequest;
import neordinary.hackathon.uteam.dto.course.response.CourseResponse;
import neordinary.hackathon.uteam.security.UserPrincipal;
import neordinary.hackathon.uteam.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@Tag(name = "Course")
@RequiredArgsConstructor
@RequestMapping("/courses")
@RestController
public class CourseController {

    private final CourseService courseService;

    @Operation(
            summary = "코스 업로드",
            description = "코스를 업로드합니다..",
            security = @SecurityRequirement(name = "access-token")
    )
    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @Valid @RequestBody CourseCreateRequest courseCreateRequest
    ) {
        CourseDto course = courseService.createCourse(userPrincipal.getUserId(), courseCreateRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(URI.create("/courses/" + course.getId()))
                .body(CourseResponse.from(course));
    }
}