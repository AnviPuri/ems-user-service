package com.ems.user.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.user.entity.Education;
import com.ems.user.entity.User;

public interface EducationRepo extends JpaRepository<Education, String> {
	
	Optional<List<Education>> findByUserAndAuditIsActive(User user, boolean isActive);

}
