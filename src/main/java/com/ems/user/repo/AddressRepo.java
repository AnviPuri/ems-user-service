package com.ems.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.user.entity.Address;

public interface AddressRepo extends JpaRepository<Address, String> {

}
