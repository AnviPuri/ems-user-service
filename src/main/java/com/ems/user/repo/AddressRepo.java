package com.ems.user.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.user.entity.Address;
import com.ems.user.entity.User;

public interface AddressRepo extends JpaRepository<Address, String> {
	
	Optional<List<Address>> findByUserAndAuditIsActive(User user, boolean isActive);

}
