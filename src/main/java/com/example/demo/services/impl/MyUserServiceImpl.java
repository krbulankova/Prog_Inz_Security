package com.example.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.models.auth.MyUser;
import com.example.demo.models.auth.MyUserDetails;
import com.example.demo.repos.IMyUserRepo;

public class MyUserServiceImpl implements UserDetailsService{

	@Autowired
	IMyUserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//find out if user is in the DB
		MyUser userFromDB = userRepo.findByUsername(username);
		//user is not in db
		if(userFromDB == null) {
			throw new UsernameNotFoundException("User not found");
		}
		else
		{	//create userDetails based on user from DB
			MyUserDetails userDetails = new MyUserDetails(userFromDB);
			return userDetails;
		}
	}

}
