package neordinary.hackathon.uteam.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import neordinary.hackathon.uteam.constant.user.RoleType;
import neordinary.hackathon.uteam.dto.user.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserPrincipal implements UserDetails {

    private UserDto userDto;

    public static UserPrincipal of(UserDto userDto) {
        return new UserPrincipal(userDto);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<RoleType> roleTypes = Set.of(RoleType.values());

        return roleTypes.stream()
                .map(RoleType::getName)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    public Long getUserId() {
        return userDto.getId();
    }

    @Override
    public String getUsername() {
        return String.valueOf(getUserId());
    }

    @Override
    public String getPassword() {
        return userDto.getSocialUid();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
