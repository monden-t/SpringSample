package com.example.demo.login.domain.repository.jdbc;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserDaoJdbcImpl implements UserDao {

	private final JdbcTemplate jdbc;

	@Override
	public int count() throws DataAccessException {
		return 0;
	}

	@Override
	public int insertOne(User user) throws DataAccessException {
		return 0;
	}

	@Override
	public User selectOne(String userId) throws DataAccessException {
		return null;
	}

	@Override
	public List<User> selectAll() throws DataAccessException {
		return null;
	}

	@Override
	public int updateOne(User user) throws DataAccessException {
		return 0;
	}

	@Override
	public int deleteOne(String userId) throws DataAccessException {
		return 0;
	}

	@Override
	public void userCsvOut() throws DataAccessException {
	}

}
