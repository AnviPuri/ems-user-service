package com.ems.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.user.entity.Education;

public interface EducationRepo extends JpaRepository<Education, String> {

}
