package neordinary.hackathon.uteam.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neordinary.hackathon.uteam.exception.auth.TokenValidateException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 모든 요청마다 작동하여, jwt token을 확인한다.
     * 유효한 token이 있는 경우 token을 parsing해서 사용자 정보를 읽고 SecurityContext에 사용자 정보를 저장한다.
     *
     * @param request     request 객체
     * @param response    response 객체
     * @param filterChain FilterChain 객체
     * @throws TokenValidateException token이 유효하지 않은 경우
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        // Header에서 JWT 받아옴
        String token = jwtTokenProvider.getToken(request);

        if (token != null) {
            jwtTokenProvider.validateToken(token);
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
