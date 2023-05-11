package com.pratice.movieapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pratice.movieapp.model.User;
import com.pratice.movieapp.service.UserService;

@RestController
public class UserController {
   
	@Autowired
	private UserService userService;
	
	
	   @PostMapping(value="/register", consumes="application/json")
	    public ResponseEntity<?> register(@RequestBody User user){
	        try {
				userService.save(user);
				 return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
			} catch (Exception e) {
				e.printStackTrace();
				 return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}

	       

	    }
	   
	   @PostMapping(value="/login", consumes="application/json")
	   public ResponseEntity<?> login(@RequestBody User user){
		   try {
				User userResponse= userService.login(user.getEmail(),user.getPassword());
				return new ResponseEntity<>(userResponse,HttpStatus.OK);
			}
			catch(Exception e) {
				String errorMessage=e.getMessage();
				e.printStackTrace();
				return new ResponseEntity<>( errorMessage,HttpStatus.BAD_REQUEST);
			}
		   
	   }
	
}
