package com.ems.user.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.user.entity.User;

public interface UserRepo extends JpaRepository<User, String> {

	Optional<List<User>> findByEmergencyContactParentAndAuditIsActive(User user, boolean isActive);

	Page<User> findByUserType(String userType, Pageable pageable);
}
