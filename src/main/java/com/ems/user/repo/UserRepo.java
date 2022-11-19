package com.ems.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.user.entity.User;

public interface UserRepo extends JpaRepository<User, String> {

}
