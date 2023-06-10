package neordinary.hackathon.uteam.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import neordinary.hackathon.uteam.dto.like.LikeDto;
import neordinary.hackathon.uteam.security.UserPrincipal;
import neordinary.hackathon.uteam.service.LikeService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Like(좋아요)")
@RequiredArgsConstructor
@RequestMapping("/likes")
@RestController
public class LikeController {

    private final LikeService likeService;

    @Operation(
            summary = "코스 좋아요하기",
            description = "<p><code>courseId</code>에 해당하는 코스를 좋아요한 코스에 추가한다.",
            security = @SecurityRequirement(name = "access-token")
    )
    @PostMapping("/courses/{courseId}")
    public LikeDto likeToCourse(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @Parameter(description = "좋아요 하고자하는 코스의 id(PK)", example = "3") @PathVariable Long courseId
    ) {
        return likeService.likeToCourse(userPrincipal.getUserId(), courseId);
    }
}
