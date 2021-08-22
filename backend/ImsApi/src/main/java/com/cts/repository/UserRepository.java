package com.cts.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.models.entityModels.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByUserName(String userName);
	
	Optional<User> findByEmailId(String emailId);
	
	List<User> findByActiveUserAndLastLogin(boolean activeUser, Date lastLogin);
	
	List<User> findByActiveUserAndLastLoginNotNull(boolean activeUser);

}
