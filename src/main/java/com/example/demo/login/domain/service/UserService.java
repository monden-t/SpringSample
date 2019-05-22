package com.example.demo.login.domain.service;

import org.springframework.stereotype.Service;

import com.example.demo.login.domain.repository.UserDao;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private UserDao dao;

}
