package com.example.demo.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BookDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userdao;

	@Override
	public User getUserById(String name) {
		// TODO Auto-generated method stub
		return userdao.getUserByName(name);
	}
	
	


}
