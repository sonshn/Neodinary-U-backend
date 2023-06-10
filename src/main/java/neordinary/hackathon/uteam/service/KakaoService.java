package neordinary.hackathon.uteam.service;

import lombok.RequiredArgsConstructor;
import neordinary.hackathon.uteam.dto.exception.ErrorResponse;
import neordinary.hackathon.uteam.dto.kakao.KakaoOAuthUserResponse;
import neordinary.hackathon.uteam.exception.kakao.KakaoServerException;
import neordinary.hackathon.uteam.exception.kakao.KakaoTokenValidateException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class KakaoService {

    private final HttpRequestService httpRequestService;

    public KakaoOAuthUserResponse getUserInfo(String accessToken) {
        String requestUrl = "https://kapi.kakao.com/v2/user/me";

        // HTTP header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        headers.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP request 보내기
        ResponseEntity<String> response;
        try {
            response = httpRequestService.sendHttpRequest(requestUrl, HttpMethod.GET, headers);
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            Integer errorCode = getErrorDetails(ex).code();
            String errorMessage = getErrorDetails(ex).message();
            if (errorCode == 401) {
                throw new KakaoTokenValidateException(errorCode, errorMessage, ex);
            }
            throw new KakaoServerException(ex.getStatusCode(), errorCode, errorMessage, ex);
        } catch (Exception ex) {
            ErrorResponse errorDetails = getErrorDetails(ex);
            throw new KakaoServerException(HttpStatus.INTERNAL_SERVER_ERROR, errorDetails.code(), errorDetails.message(), ex);
        }

        Map<String, Object> attributes = new JSONObject(response.getBody()).toMap();
        return KakaoOAuthUserResponse.from(attributes);
    }

    private ErrorResponse getErrorDetails(Exception ex) {
        int errorCode = 0;
        String errorMessage = ex.getMessage();
        if (errorMessage != null) {
            errorMessage = errorMessage.replace("\"", "");
            errorCode = parseErrorCode(errorMessage);
        }
        return new ErrorResponse(errorCode, errorMessage);
    }

    private int parseErrorCode(String errorMessage) {
        if (!errorMessage.contains("code:")) {
            return 0;
        }
        int errorCodeStartIdx = errorMessage.indexOf("code:-");
        int errorCodeEndIdx = errorMessage.indexOf("}");
        return Integer.parseInt(errorMessage.substring(errorCodeStartIdx + 6, errorCodeEndIdx));
    }
}
