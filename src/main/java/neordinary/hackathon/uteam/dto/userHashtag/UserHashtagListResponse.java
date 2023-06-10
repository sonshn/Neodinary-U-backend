package neordinary.hackathon.uteam.dto.userHashtag;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserHashtagListResponse {

    private List<UserHashtagDto> userHashtagDtos;

    public static UserHashtagListResponse of(List<UserHashtagDto> userHashtagDtos) {
        return new UserHashtagListResponse(userHashtagDtos);
    }
}
