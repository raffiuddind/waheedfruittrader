package com.waheedfruittrader.mapper;

import com.waheedfruittrader.model.entity.Role;
import com.waheedfruittrader.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * MyBatis mapper interface for User operations.
 */
@Mapper
public interface UserMapper {

    List<User> findAll();

    User findById(Long id);

    User findByUsername(String username);

    User findByEmail(String email);

    int insert(User user);

    int update(User user);

    int deleteById(Long id);

    List<Role> findRolesByUserId(Long userId);

    int insertUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

    int deleteUserRoles(Long userId);

    List<Role> findAllRoles();

    Role findRoleByName(String name);
}
