package com.pratice.movieapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pratice.movieapp.model.User;
import com.pratice.movieapp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public void save(User user) throws Exception {
		if(user.getName()==null|| user.getName().equals("")) {
			throw new Exception ("Name Cannot Be Blank");
		}else if(user.getEmail()==null|| user.getEmail().equals("")) {
			throw new Exception ("Email Cannot Be Blank");
		}else if(user.getPassword()==null|| user.getPassword().equals("")) {
			throw new Exception ("Password Cannot Be Blank");
		}
		
		Optional<User> findByEmail = userRepository.findByEmail(user.getEmail());
		  if(findByEmail.isPresent()){
	            throw new Exception("Email is already taken!");
	        }
		userRepository.save(user);
		
	}





	@Override
	public User login(String email, String password) throws Exception {
		
		User user= userRepository.findByEmailAndPassword(email,password);
		if(user == null) {
			throw new Exception("Invalid Login Credentials");
		}
		else if (!user.getPassword().equals(password)) {
			throw new Exception("Invalid Login Credentials");
		}
		
		return user;
		
	}

}
