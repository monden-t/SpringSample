package com.example.demo.login.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private UserDao dao;

	public boolean insert(User user) {
		return dao.insertOne(user);
	}

	public int count() {
		return dao.count();
	}

	public List<User> selectAll() {
		return dao.selectAll();
	}

	public User selectOne(String userId) {
		return dao.selectOne(userId);
	}
}
