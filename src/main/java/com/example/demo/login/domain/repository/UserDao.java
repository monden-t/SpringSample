package com.example.demo.login.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.login.domain.model.User;

public interface UserDao {

	public int count() throws DataAccessException;

	public boolean insertOne(User user) throws DataAccessException;

	public User selectOne(String userId) throws DataAccessException;

	public List<User> selectAll() throws DataAccessException;

	public boolean updateOne(User user) throws DataAccessException;

	public boolean deleteOne(String userId) throws DataAccessException;

	public void userCsvOut() throws DataAccessException;
}
