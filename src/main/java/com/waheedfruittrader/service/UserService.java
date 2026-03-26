package com.waheedfruittrader.service;

import com.waheedfruittrader.exception.BusinessException;
import com.waheedfruittrader.exception.ResourceNotFoundException;
import com.waheedfruittrader.mapper.UserMapper;
import com.waheedfruittrader.model.dto.UserDTO;
import com.waheedfruittrader.model.entity.Role;
import com.waheedfruittrader.model.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for User management operations.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public List<UserDTO> getAllUsers() {
        return userMapper.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        User user = userMapper.findById(id);
        if (user == null) throw new ResourceNotFoundException("User", id);
        user.setRoles(userMapper.findRolesByUserId(id));
        return toDTO(user);
    }

    @Transactional
    public UserDTO createUser(UserDTO dto) {
        if (userMapper.findByUsername(dto.getUsername()) != null) {
            throw new BusinessException("Username already exists: " + dto.getUsername());
        }
        User user = toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setActive(true);
        userMapper.insert(user);

        // Assign roles
        if (dto.getRoles() != null) {
            for (String roleName : dto.getRoles()) {
                Role role = userMapper.findRoleByName(roleName);
                if (role != null) {
                    userMapper.insertUserRole(user.getId(), role.getId());
                }
            }
        }
        log.info("Created user: {}", user.getUsername());
        return getUserById(user.getId());
    }

    @Transactional
    public UserDTO updateUser(Long id, UserDTO dto) {
        if (userMapper.findById(id) == null) throw new ResourceNotFoundException("User", id);
        dto.setId(id);
        User user = toEntity(dto);
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        userMapper.update(user);

        // Update roles
        if (dto.getRoles() != null) {
            userMapper.deleteUserRoles(id);
            for (String roleName : dto.getRoles()) {
                Role role = userMapper.findRoleByName(roleName);
                if (role != null) {
                    userMapper.insertUserRole(id, role.getId());
                }
            }
        }
        return getUserById(id);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (userMapper.findById(id) == null) throw new ResourceNotFoundException("User", id);
        userMapper.deleteUserRoles(id);
        userMapper.deleteById(id);
    }

    private UserDTO toDTO(User u) {
        List<String> roles = u.getRoles() != null
                ? u.getRoles().stream().map(Role::getName).collect(Collectors.toList())
                : List.of();
        return UserDTO.builder()
                .id(u.getId()).username(u.getUsername()).fullName(u.getFullName())
                .email(u.getEmail()).phone(u.getPhone()).active(u.getActive())
                .createdAt(u.getCreatedAt()).roles(roles).build();
    }

    private User toEntity(UserDTO dto) {
        return User.builder()
                .id(dto.getId()).username(dto.getUsername()).fullName(dto.getFullName())
                .email(dto.getEmail()).phone(dto.getPhone())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .build();
    }
}
