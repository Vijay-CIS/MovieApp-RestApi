package com.pratice.movieapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pratice.movieapp.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByName(String name);

	Optional<User> findByEmail(String email);
	
	User findByEmailAndPassword(String email, String password);


}
