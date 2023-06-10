package neordinary.hackathon.uteam.dto.user.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserListResponse {

    private List<UserResponse> users;

    public static UserListResponse of(List<UserResponse> users) {
        return new UserListResponse(users);
    }
}
