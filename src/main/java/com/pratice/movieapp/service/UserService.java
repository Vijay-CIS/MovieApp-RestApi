package com.pratice.movieapp.service;

import com.pratice.movieapp.model.User;

public interface UserService {

	void save(User user) throws Exception;
   


	User login(String email, String password) throws Exception;



}
