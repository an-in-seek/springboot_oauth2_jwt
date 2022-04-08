package com.example.oauth2jwt.security.jwt.controller;

import com.example.oauth2jwt.common.dto.MessageResponse;
import com.example.oauth2jwt.security.oauth2.enums.SocialType;
import com.example.oauth2jwt.common.exception.BadRequestException;
import com.example.oauth2jwt.security.jwt.dto.JwtResponse;
import com.example.oauth2jwt.security.oauth2.dto.LoginRequest;
import com.example.oauth2jwt.security.oauth2.dto.SignUpRequest;
import com.example.oauth2jwt.security.jwt.service.JwtService;
import com.example.oauth2jwt.user.entity.User;
import com.example.oauth2jwt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtService.generateAccessToken(authentication);
        String refreshToken = jwtService.generateAccessToken(authentication);
        return ResponseEntity.ok(JwtResponse.builder()
                .email(loginRequest.getEmail())
                .accessToken(accessToken)
                .refreshToken(refreshToken).build()
        );
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Error: Email address already in use!");
        }
        User user = User.builder()
                .username(signUpRequest.getName())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .socialType(SocialType.LOCAL).build();
        userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/me").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).body(new MessageResponse("User registered successfully!"));
    }
}
