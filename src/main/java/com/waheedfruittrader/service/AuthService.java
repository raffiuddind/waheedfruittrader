package com.waheedfruittrader.service;

import com.waheedfruittrader.exception.UnauthorizedException;
import com.waheedfruittrader.mapper.UserMapper;
import com.waheedfruittrader.model.dto.LoginRequest;
import com.waheedfruittrader.model.dto.LoginResponse;
import com.waheedfruittrader.model.entity.User;
import com.waheedfruittrader.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for authentication operations.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMapper userMapper;

    /**
     * Authenticate user and return JWT token.
     */
    public LoginResponse login(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String token = jwtTokenProvider.generateToken(userDetails);

        User user = userMapper.findByUsername(request.getUsername());
        if (user == null) {
            throw new UnauthorizedException("User not found after authentication");
        }
        user.setLastLogin(LocalDateTime.now());
        userMapper.update(user);

        List<String> roles = userMapper.findRolesByUserId(user.getId())
                .stream().map(r -> r.getName()).collect(Collectors.toList());

        log.info("User logged in: {}", request.getUsername());
        return LoginResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .userId(user.getId())
                .username(request.getUsername())
                .fullName(user.getFullName())
                .roles(roles)
                .build();
    }
}
