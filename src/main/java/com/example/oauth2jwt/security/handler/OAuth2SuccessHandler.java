package com.example.oauth2jwt.security.handler;

import com.example.oauth2jwt.common.enums.SocialType;
import com.example.oauth2jwt.security.dto.JwtResponseDto;
import com.example.oauth2jwt.security.util.JwtUtil;
import com.example.oauth2jwt.user.entity.User;
import com.example.oauth2jwt.user.entity.UserRole;
import com.example.oauth2jwt.user.repository.UserRepository;
import com.example.oauth2jwt.user.repository.UserRoleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.oauth2jwt.common.enums.SocialType.GOOGLE;
import static com.example.oauth2jwt.common.enums.SocialType.FACEBOOK;
import static com.example.oauth2jwt.common.enums.SocialType.KAKAO;

import static com.example.oauth2jwt.common.enums.UserRoleType.ROLE_ADMIN;
import static com.example.oauth2jwt.common.enums.UserRoleType.ROLE_MODERATOR;
import static com.example.oauth2jwt.common.enums.UserRoleType.ROLE_USER;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // 변수 세팅
        DefaultOidcUser userPrincipal = (DefaultOidcUser) authentication.getPrincipal();
        String id = userPrincipal.getAttribute("sub");
        String username = userPrincipal.getAttribute("name");
        String email = userPrincipal.getAttribute("email");
        List<String> userRoleList = userPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // SocialType 세팅
        OAuth2AuthenticationToken oauth2Auth = (OAuth2AuthenticationToken) authentication;
        String authority = oauth2Auth.getAuthorizedClientRegistrationId();
        SocialType socialType = null;
        if(FACEBOOK.isEquals(authority)) socialType = FACEBOOK;
        else if(GOOGLE.isEquals(authority)) socialType = GOOGLE;
        else if(KAKAO.isEquals(authority)) socialType = KAKAO;

        // Role 세팅
        Set<UserRole> userRoleSet = new HashSet<>();
        userRoleList.forEach(userRole -> {
            if(ROLE_ADMIN.isEquals(userRole)) userRoleSet.add(userRoleRepository.findByName(ROLE_ADMIN).orElse(null));
            else if(ROLE_MODERATOR.isEquals(userRole)) userRoleSet.add(userRoleRepository.findByName(ROLE_MODERATOR).orElse(null));
            else if(ROLE_USER.isEquals(userRole)) userRoleSet.add(userRoleRepository.findByName(ROLE_USER).orElse(null));
        });

        // Save User
        User user = User.builder()
                .principal(id)
                .username(username)
                .email(email)
                .socialType(socialType)
                .roles(userRoleSet).build();
        userRepository.save(user);

        // 엑세스 토큰 생성
        String accessToken = jwtUtil.generateAccessToken(authentication);

        // 리프레시 토큰 생성
        String refreshToken = jwtUtil.generateAccessToken(authentication);

        // Response Header
        response.setContentType("application/json;charset=UTF-8");

        // Response Body
        JwtResponseDto jwtResponse = JwtResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .id(id)
                .username(username)
                .email(email)
                .roles(userRoleList).build();
        String jwtResponseJson = objectMapper.writeValueAsString(jwtResponse);
        PrintWriter writer = response.getWriter();
        writer.println(jwtResponseJson);
        writer.flush();
    }
}
