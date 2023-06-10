package neordinary.hackathon.uteam.dto.exception;

public record ValidationErrorDetails(
        Integer code,
        String field,
        String message
) {
}
