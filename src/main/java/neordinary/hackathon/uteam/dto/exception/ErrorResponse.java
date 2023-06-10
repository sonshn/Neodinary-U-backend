package neordinary.hackathon.uteam.dto.exception;

public record ErrorResponse(
        Integer code,
        String message
) {
}
