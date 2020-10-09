package com.example.demo.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.auth.MyUser;

public interface IMyUserRepo extends CrudRepository < MyUser,Integer>{

	MyUser findByUsername(String username);



}
