package neordinary.hackathon.uteam.security;

import neordinary.hackathon.uteam.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class CustomUserDetailsService {

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return username -> UserPrincipal.of(userService.findDtoById(Long.valueOf(username)));
    }
}
