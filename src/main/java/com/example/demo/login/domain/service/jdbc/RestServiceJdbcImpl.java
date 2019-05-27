package com.example.demo.login.domain.service.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;
import com.example.demo.login.domain.service.RestService;

@Transactional
@Service
public class RestServiceJdbcImpl implements RestService {

	public RestServiceJdbcImpl(@Qualifier("UserDaoNamedJdbcImpl") UserDao dao) {
		super();
		this.dao = dao;
	}

	private final UserDao dao;

	@Override
	public boolean insert(User user) {
		return dao.insertOne(user);
	}

	@Override
	public User selectOne(String userId) {
		return dao.selectOne(userId);
	}

	@Override
	public List<User> selectAll() {
		return dao.selectAll();
	}

	@Override
	public boolean update(User user) {
		return dao.updateOne(user);
	}

	@Override
	public boolean delete(String userId) {
		return dao.deleteOne(userId);
	}

}
