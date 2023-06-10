package neordinary.hackathon.uteam.service;

import lombok.RequiredArgsConstructor;
import neordinary.hackathon.uteam.constant.user.LoginType;
import neordinary.hackathon.uteam.dto.auth.response.TokenResponse;
import neordinary.hackathon.uteam.dto.redis.RefreshToken;
import neordinary.hackathon.uteam.exception.auth.TokenValidateException;
import neordinary.hackathon.uteam.repository.RefreshTokenRepository;
import neordinary.hackathon.uteam.security.JwtTokenInfoDto;
import neordinary.hackathon.uteam.security.JwtTokenProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class JwtTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Access token과 refresh token을 생성한다.
     * 생성된 refresh token은 redis에 저장한다.
     * 이후, 생성된 access token과 refresh token 정보를 반환한다.
     *
     * @param userId  jwt token을 생성하고자 하는 회원의 id
     * @param loginType jwt token을 생성하고자 하는 회원의 login type
     * @return 생성된 access token과 refresh token 정보가 담긴 <code>TokenResponse</code> 객체
     */
    @Transactional
    public TokenResponse create(Long userId, LoginType loginType) {
        JwtTokenInfoDto accessTokenInfo = jwtTokenProvider.createAccessToken(userId, loginType);
        JwtTokenInfoDto refreshTokenInfo = jwtTokenProvider.createRefreshToken(userId, loginType);
        refreshTokenRepository.save(RefreshToken.of(refreshTokenInfo.token(), userId));

        return TokenResponse.of(
                accessTokenInfo.token(), accessTokenInfo.expiresAt(),
                refreshTokenInfo.token(), refreshTokenInfo.expiresAt()
        );
    }

    /**
     * Refresh token을 전달받아 갱신한다.
     * 갱신 과정에서 기존 refresh token은 redis에서 삭제되고,
     * 새로운 access token과 refresh token이 생성된다. 새롭게 생성된 refresh token은 redis에 저장된다.
     *
     * @param oldRefreshToken 기존 발급받은 refresh token
     * @return 새롭게 생성된 access token과 refresh token 정보가 담긴 <code>TokenResponse</code> 객체
     * @throws TokenValidateException 유효하지 않은 token인 경우
     */
    @Transactional
    public TokenResponse refresh(String oldRefreshToken) {
        jwtTokenProvider.validateToken(oldRefreshToken);

        RefreshToken oldRedisRefreshToken = refreshTokenRepository.findById(oldRefreshToken)
                .orElseThrow(TokenValidateException::new);
        refreshTokenRepository.delete(oldRedisRefreshToken);

        return create(
                oldRedisRefreshToken.getUserId(),
                jwtTokenProvider.getLoginType(oldRefreshToken)
        );
    }
}
