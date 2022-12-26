package com.ems.user.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.user.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

	Optional<List<User>> findByEmergencyContactParentAndAuditIsActive(User user, boolean isActive);

	Page<User> findByUserTypeAndAuditIsActive(String userType, boolean isActive, Pageable pageable);

	Page<User> findByUserTypeAndAuditIsActiveAndFirstNameContainingIgnoreCase(String userType, boolean isActive,
			String firstName, Pageable pageable);

	Optional<List<User>> findByUserIdInAndAuditIsActive(List<String> userIdList, boolean isActive);

}
