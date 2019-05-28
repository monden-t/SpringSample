package com.example.demo.login.domain.service.mybatis;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.mybatis.UserXMLMapper;
import com.example.demo.login.domain.service.RestService;

import lombok.RequiredArgsConstructor;

@Transactional
@Service("RestServiceMybatisImpl")
@RequiredArgsConstructor
public class RestServiceMybatisImpl implements RestService {

	private final UserXMLMapper userMapper;

	@Override
	public boolean insert(User user) {
		return userMapper.insert(user);
	}

	@Override
	public User selectOne(String userId) {
		return userMapper.selectOne(userId);
	}

	@Override
	public List<User> selectAll() {
		return userMapper.selectAll();
	}

	@Override
	public boolean update(User user) {
		return userMapper.updateOne(user);
	}

	@Override
	public boolean delete(String userId) {
		return userMapper.deleteOne(userId);
	}

}
