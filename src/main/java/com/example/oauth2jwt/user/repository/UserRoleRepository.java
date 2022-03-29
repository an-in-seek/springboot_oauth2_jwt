package com.example.oauth2jwt.user.repository;


import com.example.oauth2jwt.common.enums.UserRoleType;
import com.example.oauth2jwt.user.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByName(UserRoleType name);
}
