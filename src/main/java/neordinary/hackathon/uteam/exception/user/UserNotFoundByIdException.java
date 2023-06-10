package neordinary.hackathon.uteam.exception.user;

import neordinary.hackathon.uteam.exception.common.NotFoundException;

public class UserNotFoundByIdException extends NotFoundException {
    public UserNotFoundByIdException(Long userId) {
        super("userId=" + userId);
    }
}
