package neordinary.hackathon.uteam.exception.place;

import neordinary.hackathon.uteam.exception.common.NotFoundException;

public class PlaceNotFoundByIdException extends NotFoundException {
    public PlaceNotFoundByIdException(Long id) {
        super("placeId={}" + id);
    }
}
