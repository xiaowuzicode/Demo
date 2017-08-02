package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.User;

public interface UserDao extends JpaRepository<User, Long>{
	
	@Query(value="select * from user u where u.name = ?1",nativeQuery=true)
	User getUserByName(String name);

}
