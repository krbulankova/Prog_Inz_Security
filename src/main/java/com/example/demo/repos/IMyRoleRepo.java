package com.example.demo.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.auth.MyRole;
import com.example.demo.models.auth.MyUser;

public interface IMyRoleRepo extends CrudRepository< MyRole, Integer> {

	MyRole findByTitle(String string);

}
