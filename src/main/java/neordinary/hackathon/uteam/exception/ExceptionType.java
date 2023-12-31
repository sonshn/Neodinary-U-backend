package neordinary.hackathon.uteam.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import neordinary.hackathon.uteam.constant.exception.ValidationErrorCode;
import neordinary.hackathon.uteam.domain.Course;
import neordinary.hackathon.uteam.domain.Like;
import neordinary.hackathon.uteam.domain.Place;
import neordinary.hackathon.uteam.domain.User;
import neordinary.hackathon.uteam.exception.auth.TokenValidateException;
import neordinary.hackathon.uteam.exception.course.CourseNotFoundByIdException;
import neordinary.hackathon.uteam.exception.kakao.KakaoTokenValidateException;
import neordinary.hackathon.uteam.exception.place.PlaceImageFindingException;
import neordinary.hackathon.uteam.exception.place.PlaceListEmptyException;
import neordinary.hackathon.uteam.exception.place.PlaceNotFoundByIdException;
import neordinary.hackathon.uteam.exception.user.UserNotFoundByIdException;
import neordinary.hackathon.uteam.logger.LogUtils;
import neordinary.hackathon.uteam.service.AlreadyLikedCourseException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.*;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
@Getter
public enum ExceptionType {

    /**
     * Global/Normal Exception
     */
    UNHANDLED(1000, "알 수 없는 서버 에러가 발생했습니다.", null),

    /**
     * Validation Exception
     *
     * @see ValidationErrorCode
     */
    METHOD_ARGUMENT_NOT_VALID(1200, "요청 데이터가 잘못되었습니다.", MethodArgumentNotValidException.class),
    CONSTRAINT_VIOLATION(1200, "요청 데이터가 잘못되었습니다.", ConstraintViolationException.class),

    /**
     * Spring MVC Default Exception
     */
    HTTP_REQUEST_METHOD_NOT_SUPPORTED(1300, "지원하지 않는 요청 방식입니다.", HttpRequestMethodNotSupportedException.class),
    HTTP_MEDIA_TYPE_NOT_SUPPORTED(1301, "지원하지 않는 요청 데이터 타입입니다.", HttpMediaTypeNotSupportedException.class),
    HTTP_MEDIA_TYPE_NOT_ACCEPTABLE(1302, "요청한 데이터 타입으로 응답을 만들어 낼 수 없습니다.", HttpMediaTypeNotAcceptableException.class),
    MISSING_PATH_VARIABLE(1303, "필요로 하는 path variable이 누락 되었습니다.", MissingPathVariableException.class),
    MISSING_SERVLET_REQUEST_PARAMETER(1304, "필요로 하는 request parameter가 누락 되었습니다.", MissingServletRequestParameterException.class),
    MISSING_REQUEST_HEADER(1305, "필요로 하는 request header가 누락 되었습니다.", MissingRequestHeaderException.class),
    SERVLET_REQUEST_BINDING(1306, "복구 불가능한 fatal binding exception이 발생했습니다.", ServletRequestBindingException.class),
    CONVERSION_NOT_SUPPORTED(1307, "Bean property에 대해 적절한 editor 또는 convertor를 찾을 수 없습니다.", ConversionNotSupportedException.class),
    TYPE_MISMATCH(1308, "Bean property를 설정하던 중 type mismatch로 인한 예외가 발생했습니다.", TypeMismatchException.class),
    HTTP_MESSAGE_NOT_READABLE(1309, "읽을 수 없는 요청입니다. 요청 정보가 잘못되지는 않았는지 확인해주세요.", HttpMessageNotReadableException.class),
    HTTP_MESSAGE_NOT_WRITABLE(1310, "응답 데이터를 생성할 수 없습니다.", HttpMessageNotWritableException.class),
    MISSING_SERVLET_REQUEST_PART(1311, "multipart/form-data 형식의 요청 데이터에 대해 일부가 손실되거나 누락되었습니다.", MissingServletRequestPartException.class),
    NO_HANDLER_FOUND(1312, "알 수 없는 에러가 발생했으며, 에러를 처리할 handler를 찾지 못했습니다.", NoHandlerFoundException.class),
    ASYNC_REQUEST_TIMEOUT(1313, "요청에 대한 응답 시간이 초과되었습니다.", AsyncRequestTimeoutException.class),
    BIND(1314, "Request binding에 실패했습니다. 요청 데이터를 확인해주세요.", BindException.class),

    /**
     * 로그인, 인증 관련 예외
     */
    ACCESS_DENIED(1500, "접근이 거부되었습니다.", null),
    UNAUTHORIZED(1501, "유효하지 않은 인증 정보로 인해 인증 과정에서 문제가 발생하였습니다.", null),
    TOKEN_VALIDATE(1502, "유효하지 않은 token입니다. Token 값이 잘못되었거나 만료되어 유효하지 않은 경우로 token 갱신이 필요합니다.", TokenValidateException.class),

    /**
     * 유저({@link User}) 관련 예외
     */
    USER_NOT_FOUND_BY_ID(2000, "일치하는 유저를 찾을 수 없습니다.", UserNotFoundByIdException.class),

    /**
     * 장소({@link Place}) 관련 예외
     */
    PLACE_NOT_FOUND_BY_ID(2500, "일치하는 장소를 찾을 수 없습니다.", PlaceNotFoundByIdException.class),
    PLACE_IMAGE_FINDING(2501, "장소 이미지를 찾던 중 알 수 없는 에러가 발생했습니다.", PlaceImageFindingException.class),
    PLACE_LIST_EMPTY(2502, "전달받은 장소 리스트가 비어있습니다.", PlaceListEmptyException.class),

    /**
     * 코스({@link Course}) 관련 예외
     */
    COURSE_NOT_FOUND_BY_ID(3000, "일치하는 코스를 찾을 수 없습니다.", CourseNotFoundByIdException.class),

    /**
     * 좋아요({@link Like}) 관련 예외
     */
    ALREADY_LIKED_COURSE(3500, "이미 좋아요 한 코스입니다.", AlreadyLikedCourseException.class),
    NOT_FOUND(3501, "좋아요한 이력을 찾을 수 없습니다.", NotFoundLike.class),

    /**
     * Kakao server 관련 예외
     */
    KAKAO_SERVER(10000, "카카오 서버와의 통신에서 에러가 발생했습니다.", null),
    KAKAO_TOKEN_VALIDATE(10000, "유효하지 않은 kakao access token입니다. 요청 데이터가 잘못 되었거나 토큰이 만료되지 않았는지 확인해주세요.", KakaoTokenValidateException.class),
    ;

    private final Integer code;
    private final String message;
    private final Class<? extends Exception> type;

    public static Optional<ExceptionType> from(Class<? extends Exception> classType) {
        Optional<ExceptionType> exType = Arrays.stream(values())
                .filter(ex -> ex.getType() != null && ex.getType().isAssignableFrom(classType))
                .findFirst();

        if (exType.isEmpty()) {
            log.error("[{}] 정의되지 않은 exception이 발생하였습니다. Type of exception={}", LogUtils.getLogTraceId(), classType);
        }

        return exType;
    }
}
